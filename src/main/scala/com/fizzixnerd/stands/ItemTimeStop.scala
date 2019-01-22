package com.fizzixnerd.stands

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.{Entity, EntityLivingBase}
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util._
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.world.{World, WorldServer}
import net.minecraftforge.common.MinecraftForge

import scala.collection.JavaConversions._

class ItemTimeStop extends Item {

  setRegistryName("time_stop")
  setUnlocalizedName("time_stop")
  maxStackSize = 1
  setCreativeTab(CreativeTabs.TOOLS)

  override
  def onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult[ItemStack] = {
    super.onItemRightClick(worldIn, playerIn, handIn)

    // need to time stop all the server entities
    // don't stop them on the client, the message will take care of that.
    if (!worldIn.isRemote) {
      val p = playerIn.getPositionVector
      val range = 16
      val AABB = new AxisAlignedBB(p.x - range, p.y - range, p.z - range, p.x + range, p.y + range, p.z + range)
      val entities = worldIn
        .getEntitiesWithinAABB(classOf[Entity], AABB)
        .filter(entity => entity != playerIn)
      entities.foreach(entity => {
        entity.updateBlocked = true
      })
      val entityIds = entities.map(entity => entity.getEntityId)
      val message = new TimeStopMessage(entityIds, true, playerIn.posX, playerIn.posY, playerIn.posZ)
      StandsPacketHandler.INSTANCE.sendToAll(message)
      val timer = new TimeStopTimer(worldIn.asInstanceOf[WorldServer], entityIds, 100, p.x, p.y, p.z)
      MinecraftForge.EVENT_BUS.register(timer)
    }
    ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn))
  }
}
