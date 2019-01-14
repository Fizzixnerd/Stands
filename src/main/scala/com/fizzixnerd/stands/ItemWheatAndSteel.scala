package com.fizzixnerd.stands

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.{EnumActionResult, EnumFacing, EnumHand, ResourceLocation}
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.IPlantable
import net.minecraftforge.fml.common.registry.GameRegistry

object ItemWheatAndSteel extends Item {
  setRegistryName("wheat_and_steel")
  maxStackSize = 1
  setMaxDamage(64)
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
    if (side == EnumFacing.UP && world.isAirBlock(pos.up)) {
      val blockState = world.getBlockState(pos)
      val block = blockState.getBlock
      val wheatSeeds = GameRegistry
        .findRegistry(classOf[Item])
        .getValue(new ResourceLocation("minecraft:wheat_seeds"))
        .asInstanceOf[Item with IPlantable]
      val canGrow = block.canSustainPlant(blockState, world, pos, EnumFacing.UP, wheatSeeds)
      if (canGrow) {
        world.setBlockState(pos.up, wheatSeeds.getPlant(world, pos))
        player.getHeldItem(hand).damageItem(1, player)
        return EnumActionResult.SUCCESS
      }
    }
    EnumActionResult.FAIL
  }
}
