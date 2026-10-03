package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.AnimalData
import com.example.data.UserProgressManager
import com.example.model.AnimalCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("ZooPals", appName)
  }

  @Test
  fun `animal data contains all required categories and attributes`() {
    val animals = AnimalData.animals
    assertTrue(animals.isNotEmpty())
    assertTrue(animals.size >= 20)

    val lion = AnimalData.getAnimalById("lion")
    assertNotNull(lion)
    assertEquals("Lion", lion?.name)
    assertEquals("Cub", lion?.babyName)
    assertEquals(AnimalCategory.SAFARI, lion?.category)

    val dolphin = AnimalData.getAnimalById("dolphin")
    assertNotNull(dolphin)
    assertEquals(AnimalCategory.OCEAN, dolphin?.category)
  }

  @Test
  fun `user progress manager stores stars and stickers`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val manager = UserProgressManager(context)
    val initialStars = manager.getTotalStars()
    assertTrue(initialStars >= 0)

    manager.addStars(5)
    assertEquals(initialStars + 5, manager.getTotalStars())

    manager.unlockSticker("elephant")
    assertTrue(manager.getUnlockedStickers().contains("elephant"))
  }
}
