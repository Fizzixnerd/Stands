package com.fizzixnerd.stands

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemHoe}
import net.minecraft.util.{EnumActionResult, EnumFacing, EnumHand, ResourceLocation}
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry

object ItemWheatAndSteel extends Item {
  setRegistryName("wheat_and_steel")
  maxStackSize = 1
  setMaxDamage(64)
  setCreativeTab(CreativeTabs.TOOLS)
  private val virtualSeeds = GameRegistry.findRegistry(classOf[Item]).getValue(new ResourceLocation(("minecraft:wheat_seeds")))

  override
  def onItemUse(player: EntityPlayer,
                world: World,
                pos: BlockPos,
                hand: EnumHand,
                side: EnumFacing,
                hitX: Float,
                hitY: Float,
                hitZ: Float): EnumActionResult = {
    val itemStack = player.getHeldItem(hand)
    if (!player.canPlayerEdit(pos.offset(side), side, itemStack)) {
      EnumActionResult.FAIL
    } else {
      // Create a virtual hoe to till the ground first.
      val virtualHoe = new ItemHoe(Item.ToolMaterial.IRON)
      player.
      val success = virtualHoe.onItemUse(player, world, pos, hand, side, hitX, hitY, hitZ)
      if (success == EnumActionResult.SUCCESS) {
        val success = virtualSeeds.onItemUse(player, world, pos, hand, side, hitX, hitY, hitZ)
        if (success == EnumActionResult.SUCCESS) {
          itemStack.damageItem(1, player)
        }
        success
      } else {
        success
      }
    }
  }
}
