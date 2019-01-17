package com.fizzixnerd.stands

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.util._
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

import scala.util.Random

class ItemTimeStop extends Item {

  setRegistryName("time_stop")
  setUnlocalizedName("time_stop")
  maxStackSize = 1
  setCreativeTab(CreativeTabs.TOOLS)

  override
  def onItemUse(player: EntityPlayer,
                world: World,
                pos: BlockPos,
                hand: EnumHand,
                side: EnumFacing,
                hitX: Float,
                hitY: Float,
                hitZ: Float): EnumActionResult = {
    world.playSound(player, pos, Stands.soundTimeStop, SoundCategory.NEUTRAL, 1.0F, 1.0F)

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
    EnumActionResult.SUCCESS
  }
}
