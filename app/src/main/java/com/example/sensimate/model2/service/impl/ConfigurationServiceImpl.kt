/*
Copyright 2022 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.example.sensimate.model2.service.impl


import com.example.sensimate.BuildConfig
import com.example.sensimate.model2.service.trace
import com.example.sensimate.R.xml as AppConfig

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

//class ConfigurationServiceImpl @Inject constructor() : ConfigurationService {
//  private val remoteConfig
//    get() = Firebase.remoteConfig
//
//  init {
//    if (BuildConfig.DEBUG) {
//      val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 0 }
//      remoteConfig.setConfigSettingsAsync(configSettings)
//    }
//
//    remoteConfig.setDefaultsAsync(AppConfig.remote_config_defaults)
//  }
//
//  override suspend fun fetchConfiguration(): Boolean =
//    trace(FETCH_CONFIG_TRACE) { remoteConfig.fetchAndActivate().await() }
//
//  override val isShowEventEditButtonConfig: Boolean
//    get() = remoteConfig[SHOW_TASK_EDIT_BUTTON_KEY].asBoolean()
//
//  companion object {
//    private const val SHOW_TASK_EDIT_BUTTON_KEY = "show_task_edit_button"
//    private const val FETCH_CONFIG_TRACE = "fetchConfig"
//  }
//}
