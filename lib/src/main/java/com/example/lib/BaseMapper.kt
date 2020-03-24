package com.example.lib

abstract class BaseMapper<Entity, Dto> {

    abstract fun map(entity: Entity):Dto

    abstract fun map(entityList: List<Entity>):List<Dto>

}