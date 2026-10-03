package com.example.model

enum class AnimalCategory(val displayName: String, val icon: String, val description: String) {
    ALL("All Pals", "🐾", "Meet all animals"),
    SAFARI("Safari", "🦁", "Wild African savanna"),
    FARM("Farm", "🐮", "Friendly barnyard friends"),
    OCEAN("Ocean", "🐬", "Deep blue sea swimmers"),
    BIRDS("Birds", "🦜", "Flying feathered pals"),
    FOREST("Forest", "🐻", "Woodland forest wanderers"),
    PETS("Pets", "🐶", "Loving home companions")
}

enum class DietType(val label: String, val icon: String) {
    HERBIVORE("Plant Eater", "🌿"),
    CARNIVORE("Meat Eater", "🥩"),
    OMNIVORE("Eats Both Plants & Fish", "🍎")
}

data class Animal(
    val id: String,
    val name: String,
    val emoji: String,
    val category: AnimalCategory,
    val soundEffect: String,
    val soundDescription: String,
    val babyName: String,
    val diet: DietType,
    val dietDetails: String,
    val habitat: String,
    val funFacts: List<String>,
    val superpower: String,
    val accentColorHex: Long,
    val bgLightHex: Long,
    val sizeDescription: String
)
