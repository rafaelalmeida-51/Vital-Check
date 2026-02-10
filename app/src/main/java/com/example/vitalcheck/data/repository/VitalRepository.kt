package com.seunome.vitalcheck.data.repository

import com.seunome.vitalcheck.model.VitalData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class VitalsRepository {

    fun observeVitals(): Flow<VitalData> = flow {
        var steps = 1000
        var heart = 80

        while (true) {
            // batimento varia pouco para parecer real
            heart = (heart + Random.nextInt(-2, 3)).coerceIn(55, 140)

            // passos só aumentam
            steps += Random.nextInt(0, 12)

            emit(VitalData(heartRateBpm = heart, steps = steps))
            delay(1500)
        }
    }
}