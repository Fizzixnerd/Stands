package com.fizzixnerd.stands

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.{Entity, EntityLivingBase}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util._
import net.minecraft.util.math.{AxisAlignedBB, BlockPos}
import net.minecraft.world.{World, WorldServer}
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.EventBus
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent

import scala.collection.JavaConversions._
import scala.util.Random

class ItemTimeStop extends Item {

  setRegistryName("time_stop")
  setUnlocalizedName("time_stop")
  maxStackSize = 1
  setCreativeTab(CreativeTabs.TOOLS)

  override
  def itemInteractionForEntity(stack: ItemStack, playerIn: EntityPlayer, target: EntityLivingBase, hand: EnumHand): Boolean = {
    super.itemInteractionForEntity(stack, playerIn, target, hand)
    if (target.updateBlocked) {
      target.updateBlocked = false
      playerIn.playSound(Stands.soundUntimeStop, 1.0F, 1.0F)
    } else {
      target.updateBlocked = true
      playerIn.playSound(Stands.soundTimeStop, 1.0F, 1.0F)
    }
    true
  }

  override
  def onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult[ItemStack] = {
    super.onItemRightClick(worldIn, playerIn, handIn)

    // need to time stop all the server entities
    // don't stop them on the client, the message will take care of that.
    if (!worldIn.isRemote) {
      val p = playerIn.getPositionVector
      val range = 10
      val entities = worldIn.getEntitiesWithinAABB(classOf[Entity], new AxisAlignedBB(p.x - range, p.y - range, p.z - range, p.x + range, p.y + range, p.z + range)).filter(entity => entity != playerIn)
      entities.foreach(entity => {
        entity.updateBlocked = true
      })
      val entityIds = entities.map(entity => entity.getEntityId)
      StandsPacketHandler.INSTANCE.sendToAll(new TimeStopMessage(entityIds, true, playerIn.posX, playerIn.posY, playerIn.posZ))
      MinecraftForge.EVENT_BUS.register(new TimeStopTimer(worldIn.asInstanceOf[WorldServer], entityIds, 100, p.x, p.y, p.z))
    }
    ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn))
  }
}
