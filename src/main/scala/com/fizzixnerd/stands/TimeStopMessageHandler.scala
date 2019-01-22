package com.fizzixnerd.stands

import net.minecraft.client.Minecraft
import net.minecraft.util.math.BlockPos
import net.minecraft.util.{EnumParticleTypes, SoundCategory}
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.simpleimpl.{IMessage, IMessageHandler, MessageContext}

import scala.util.Random

class TimeStopMessageHandler extends IMessageHandler[TimeStopMessage, IMessage] {
  def spawnParticles(world: World, pos: BlockPos): Unit = {
    val range = 16.0
    for (_ <- 0 until 50000) {
      val randX = Random.nextDouble()
      val randZ = Random.nextDouble()
      val randPlusX = Random.nextBoolean()
      val randPlusY = Random.nextBoolean()
      val randPlusZ = Random.nextBoolean()
      val d0 = pos.getX + range * (if (randPlusX) randX else -randX)
      val d1 = pos.getY + (if (randPlusY) randX else -randX) * 6.0 / 16.0
      val d2 = pos.getZ + range * (if (randPlusZ) randZ else -randZ)
      world.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, range * (if (randPlusX) randX else -randX), 1.0, range * (if (randPlusZ) randZ else -randZ))
    }
  }

  override
  def onMessage(message: TimeStopMessage, ctx: MessageContext): IMessage = {
    Minecraft.getMinecraft.addScheduledTask(new Runnable {
      override
      def run(): Unit = {
        val world = Minecraft.getMinecraft.world
        message.entityIds.foreach(id => {
          val e = world.getEntityByID(id)
          if (e != null) {
            e.updateBlocked = message.updateBlocked
          }
        })
        if (message.updateBlocked) {
          world.playSound(message.x, message.y, message.z, Stands.soundTimeStop, SoundCategory.NEUTRAL, 1.0F, 1.0F, false)
        } else {
          world.playSound(message.x, message.y, message.z, Stands.soundUntimeStop, SoundCategory.NEUTRAL, 1.0F, 1.0F, false)
        }
        spawnParticles(world, new BlockPos(message.x, message.y, message.z))
      }
    })
    null
  }
}
