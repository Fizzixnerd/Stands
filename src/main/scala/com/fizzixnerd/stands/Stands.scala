package com.fizzixnerd.stands

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.util.{ResourceLocation, SoundEvent}
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.{Mod, SidedProxy}
import net.minecraftforge.fml.common.Mod.{EventBusSubscriber, EventHandler}
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.apache.logging.log4j.Logger

@Mod(modid = "stands", name = "Stands", version = "1.0.0", modLanguage = "scala")
@EventBusSubscriber
object Stands {
  var logger: Logger = _

  val blockCool = new BlockCool

  val BLOCKS = Array(blockCool)
  val ITEMS =  Array(blockCool.itemBlock, new ItemWheatAndSteel, new ItemTimeStop)

  val soundTimeStop = new SoundEvent(new ResourceLocation("stands", "time_stop_sound"))
  soundTimeStop.setRegistryName("time_stop_sound")

  val soundUntimeStop = new SoundEvent(new ResourceLocation("stands", "untime_stop_sound"))
  soundUntimeStop.setRegistryName("untime_stop_sound")

  val SOUNDS = Array(soundTimeStop, soundUntimeStop)

  @SidedProxy(clientSide = "com.fizzixnerd.stands.ClientProxy", serverSide = "com.fizzixnerd.stands.ServerProxy")
  var proxy: IProxy = _

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) {
    logger = event.getModLog
    proxy.preInit(event)
  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    proxy.init(event)
  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {
    proxy.postInit(event)
  }

  @SubscribeEvent
  def registerBlocks(event: RegistryEvent.Register[Block]): Unit = {
    logger.info("Registering block")
    event.getRegistry.registerAll(BLOCKS: _*)
  }

  @SubscribeEvent
  def registerItems(event: RegistryEvent.Register[Item]): Unit = {
    logger.info("Registering items")
    event.getRegistry.registerAll(ITEMS: _*)
  }
}
