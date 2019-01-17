package com.fizzixnerd.stands

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.util._
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

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
    EnumActionResult.SUCCESS
  }
}
