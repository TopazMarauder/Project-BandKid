package com.bandkid.game.di

import dagger.Component

@CoreScope
@Component(modules = [CoreModule::class])
interface CoreComponent {
}