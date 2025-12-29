package com.example.metrosample.bottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface BottomNavKey: NavKey {
    val icon: ImageVector
    val label: String

    @Serializable
    data object Screen1: BottomNavKey {
        override val icon: ImageVector = Icons.Default.Home
        override val label: String = "Screen1"
    }

    @Serializable
    data object Screen2: BottomNavKey {
        override val icon: ImageVector = Icons.Default.AccountCircle
        override val label: String = "Screen2"
    }

    companion object {
        val keys: List<BottomNavKey> = listOf(
            Screen1,
            Screen2,
        )

        /**
         * NOTE:
         *     sealed は標準で enum class の values の様なものがない。
         *     そのため、新たに sub class を定義したときに values への追加を失念する場合がある。
         *     これ防ぐために以下でコンパイルエラーが起きる様にしている。
         *     コンパイルエラーが起きた場合は、values 及び init の修正を行うこと。
         */
        init {
            keys.forEach { value ->
                when(value) {
                    Screen1 -> Unit
                    Screen2 -> Unit
                }
            }
        }
    }
}