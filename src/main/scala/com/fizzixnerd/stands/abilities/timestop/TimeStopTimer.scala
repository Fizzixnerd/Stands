package com.fizzixnerd.stands.abilities.timestop

import com.fizzixnerd.stands.net.StandsPacketHandler
import net.minecraft.world.WorldServer
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

class TimeStopTimer(world: WorldServer, entityIds: Seq[Int], var timeLeft: Int, x: Double, y: Double, z: Double) {
  @SubscribeEvent
  def onServerTick(event: TickEvent.ServerTickEvent): Unit = {
    if (timeLeft < 1) {
      MinecraftForge.EVENT_BUS.unregister(this)
      entityIds.foreach(id => {
        val e = world.getEntityByID(id)
        if (e != null) {
          e.updateBlocked = false
        }
      })
      StandsPacketHandler.INSTANCE.sendToAll(new TimeStopMessage(entityIds, false, x, y, z))
    } else {
      timeLeft -= 1
    }
  }

}
