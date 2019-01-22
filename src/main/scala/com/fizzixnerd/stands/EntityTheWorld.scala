package com.fizzixnerd.stands

import net.minecraft.entity.{EntityAgeable, SharedMonsterAttributes}
import net.minecraft.entity.ai._
import net.minecraft.entity.passive.EntityTameable
import net.minecraft.world.World

class EntityTheWorld(world: World) extends EntityTameable(world) {
  setSize(0.8F, 2.2F)

  override
  def createChild(ageable: EntityAgeable): EntityAgeable = {
    new EntityTheWorld(world)
  }

  override
  def applyEntityAttributes(): Unit = {
    super.applyEntityAttributes()
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(2.0D)
    getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D)
    getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D)
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D)
  }

  override
  def initEntityAI(): Unit = {
    tasks.addTask(1, new EntityAISwimming(this))
    tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true))
    tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F))
    tasks.addTask(8, new EntityAIWander(this, 1.0F))
    tasks.addTask(10, new EntityAILookIdle(this))
    targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this))
    targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this))
    targetTasks.addTask(3, new EntityAIHurtByTarget(this, true))
  }
}
