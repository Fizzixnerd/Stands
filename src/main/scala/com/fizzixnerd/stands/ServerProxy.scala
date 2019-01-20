package com.fizzixnerd.stands
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.relauncher.Side

class ServerProxy extends IProxy {
  override
  def preInit(event: FMLPreInitializationEvent): Unit = {
    Stands.logger.info("Calling ServerProxy PreInit.")
    StandsPacketHandler.INSTANCE.registerMessage(classOf[TimeStopMessageHandler], classOf[TimeStopMessage], StandsPacketHandler.id, Side.CLIENT)
    StandsPacketHandler.id += 1
  }

  override
  def init(event: FMLInitializationEvent): Unit = {}

  override
  def postInit(event: FMLPostInitializationEvent): Unit = {}
}
