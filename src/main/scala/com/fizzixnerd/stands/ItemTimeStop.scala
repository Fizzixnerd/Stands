package com.fizzixnerd.stands

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.{Entity, EntityLivingBase}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util._
import net.minecraft.util.math.{AxisAlignedBB, BlockPos}
import net.minecraft.world.World
import scala.collection.JavaConversions._

import scala.util.Random

class ItemTimeStop extends Item {

  setRegistryName("time_stop")
  setUnlocalizedName("time_stop")
  maxStackSize = 1
  setCreativeTab(CreativeTabs.TOOLS)

  def spawnParticles(world: World, pos: BlockPos): Unit = {
    for (_ <- 1 to 50000) {
      val randX = Random.nextDouble()
      val randZ = Random.nextDouble()
      val randPlusX = Random.nextBoolean()
      val randPlusY = Random.nextBoolean()
      val randPlusZ = Random.nextBoolean()
      val d0 = pos.getX + 20.0 * (if (randPlusX) randX else -randX)
      val d1 = pos.getY + (if (randPlusY) randX else -randX) * 6.0 / 16.0
      val d2 = pos.getZ + 20.0 * (if (randPlusZ) randZ else -randZ)
      world.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 20.0 * (if (randPlusX) randX else -randX), 1.0, 20.0 * (if (randPlusZ) randZ else -randZ))
    }
  }

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
    spawnParticles(playerIn.getEntityWorld, playerIn.getPosition)
    true
  }

  override
  def onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult[ItemStack] = {
    super.onItemRightClick(worldIn, playerIn, handIn)
    val p = playerIn.getPositionVector
    val range = 10
    val entities = worldIn.getEntitiesWithinAABB(classOf[Entity], new AxisAlignedBB(p.x - range, p.y - range, p.z - range, p.x + range, p.y + range, p.z + range))
    playerIn.playSound(Stands.soundTimeStop, 1.0F, 1.0F)
    entities.foreach((entity: Entity) => {
      if (entity != playerIn) {
        entity.updateBlocked = !entity.updateBlocked
      }
    })
    spawnParticles(playerIn.getEntityWorld, playerIn.getPosition)
    ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn))
  }
}
