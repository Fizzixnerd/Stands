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
  val ITEMS: Array[Item] = Array(BlockCool.itemBlock, ItemWheatAndSteel)

  @SubscribeEvent
  def registerItems(event: RegistryEvent.Register[Item]): Unit = {
    logger.debug("Registering items")
    event.getRegistry.registerAll(ITEMS: _*)
  }

  @SubscribeEvent
  def registerModels(event: ModelRegistryEvent): Unit = {
    logger.debug("Registering item models.")
    ITEMS.foreach((item: Item) => {
      val registeredItem = GameRegistry.findRegistry(classOf[Item]).getValue(item.getRegistryName)
      ModelLoader.setCustomModelResourceLocation(
        registeredItem,
        0,
        new ModelResourceLocation(registeredItem.getRegistryName, "normal")
      )
    })
  }
}