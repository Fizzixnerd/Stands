package com.fizzixnerd.stands

import com.fizzixnerd.stands.Stands.logger
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry

@Mod.EventBusSubscriber
object RegisterItems {
  @SubscribeEvent
  def registerItems(event: RegistryEvent.Register[Item]): Unit = {
    logger.info("Registering item blocks")
    event.getRegistry.register(BlockCool.itemBlock)
  }

  @SubscribeEvent
  def registerModels(event: ModelRegistryEvent): Unit = {
    logger.info("Registering item models.")
    val coolItem = GameRegistry.findRegistry(classOf[Item]).getValue(BlockCool.itemBlock.getRegistryName)
    ModelLoader.setCustomModelResourceLocation(
      coolItem,
      0,
      new ModelResourceLocation(BlockCool.itemBlock.getRegistryName, "normal")
    )
  }
}
