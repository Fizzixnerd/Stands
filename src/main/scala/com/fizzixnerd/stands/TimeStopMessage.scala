package com.fizzixnerd.stands

import io.netty.buffer.ByteBuf
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

// x y and z are the coordinates of the time-stopping player.
class TimeStopMessage(var entityIds: Seq[Int], var updateBlocked: Boolean, var x: Double, var y: Double, var z: Double) extends IMessage {
  def this() {
    this(Vector[Int](), false, 0, 0, 0)
  }

  override
  def toBytes(buf: ByteBuf): Unit = {
    buf.writeBoolean(updateBlocked)
    buf.writeInt(entityIds.length)
    for (entityId <- entityIds) {
      buf.writeInt(entityId)
    }
    buf.writeDouble(x)
    buf.writeDouble(y)
    buf.writeDouble(z)
  }

  override
  def fromBytes(buf: ByteBuf): Unit = {
    updateBlocked = buf.readBoolean()
    val numIds = buf.readInt()
    entityIds = Vector[Int]()
    for (_ <- 0 until numIds) {
      entityIds = entityIds.+:(buf.readInt())
    }
    x = buf.readDouble()
    y = buf.readDouble()
    z = buf.readDouble()
  }
}
