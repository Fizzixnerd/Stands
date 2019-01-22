package com.fizzixnerd.stands

import net.minecraft.block.Block
import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.util.{ResourceLocation, SoundEvent}
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.{Mod, SidedProxy}
import net.minecraftforge.fml.common.Mod.{EventBusSubscriber, EventHandler}
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.{EntityEntry, EntityEntryBuilder}
import org.apache.logging.log4j.Logger

@Mod(modid = "stands", name = "Stands", version = "1.0.0", modLanguage = "scala")
@EventBusSubscriber
object Stands {
  val MODID = "stands"
  var logger: Logger = _

  val blockCool = new BlockCool

  val BLOCKS = Array(blockCool)
  val ITEMS =  Array(blockCool.itemBlock, new ItemWheatAndSteel, new ItemTimeStop)

  val soundTimeStop = new SoundEvent(new ResourceLocation(Stands.MODID, "time_stop_sound"))
  soundTimeStop.setRegistryName("time_stop_sound")

  val soundUntimeStop = new SoundEvent(new ResourceLocation(Stands.MODID, "untime_stop_sound"))
  soundUntimeStop.setRegistryName("untime_stop_sound")

  val SOUNDS = Array(soundTimeStop, soundUntimeStop)

  var ID = 0
  val theWorldEntry: EntityEntry = EntityEntryBuilder.create()
    .entity(classOf[EntityTheWorld])
    .id(new ResourceLocation(Stands.MODID, "the_world"), ID)
    .name("the_world")
    .egg(0xFFFFFF, 0xAAAAAA)
    .tracker(64, 20, false)
    .build()
  ID += 1

  val ENTITYENTRIES = Array(theWorldEntry)

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
    logger.info("Registering block.")
    event.getRegistry.registerAll(BLOCKS: _*)
  }

  @SubscribeEvent
  def registerItems(event: RegistryEvent.Register[Item]): Unit = {
    logger.info("Registering items.")
    event.getRegistry.registerAll(ITEMS: _*)
  }

  @SubscribeEvent
  def registerEntities(event: RegistryEvent.Register[EntityEntry]): Unit = {
    logger.info("Registering entities.")
    event.getRegistry.registerAll(ENTITYENTRIES: _*)
  }
}
