package com.example.data

import com.example.model.Animal
import com.example.model.AnimalCategory
import com.example.model.DietType

object AnimalData {
    val animals = listOf(
        Animal(
            id = "lion",
            name = "Lion",
            emoji = "🦁",
            category = AnimalCategory.SAFARI,
            soundEffect = "Roaaar!",
            soundDescription = "Mighty King's Roar",
            babyName = "Cub",
            diet = DietType.CARNIVORE,
            dietDetails = "Hunts meat with its pride",
            habitat = "Savanna Grasslands 🌾",
            funFacts = listOf(
                "Lions are called the 'King of the Jungle', but they mostly live in open savannas!",
                "A lion's roar can be heard from 5 miles (8 kilometers) away!",
                "Lion families live in groups called a 'Pride'."
            ),
            superpower = "Super Loud Roar that commands respect across the plains!",
            accentColorHex = 0xFFFF9800,
            bgLightHex = 0xFFFFF3E0,
            sizeDescription = "Very Big & Strong (Up to 420 lbs!)"
        ),
        Animal(
            id = "elephant",
            name = "Elephant",
            emoji = "🐘",
            category = AnimalCategory.SAFARI,
            soundEffect = "Pawoooot!",
            soundDescription = "Trumpeting Trunk",
            babyName = "Calf",
            diet = DietType.HERBIVORE,
            dietDetails = "Eats grass, tree bark, fruit, and leaves 🍃",
            habitat = "African & Asian Forests and Plains 🌳",
            funFacts = listOf(
                "Elephants are the largest land animals in the whole world!",
                "They use their long trunk like a super hand to drink water, hug friends, and pick berries.",
                "Elephant ears look like fans and help keep them cool in the hot sunshine!"
            ),
            superpower = "Amazing memory! Elephants never forget friends and watering holes.",
            accentColorHex = 0xFF5C6BC0,
            bgLightHex = 0xFFE8EAF6,
            sizeDescription = "Giant of the Earth (Up to 13,000 lbs!)"
        ),
        Animal(
            id = "giraffe",
            name = "Giraffe",
            emoji = "🦒",
            category = AnimalCategory.SAFARI,
            soundEffect = "Hummm-Munch!",
            soundDescription = "Gentle Soft Hum",
            babyName = "Calf",
            diet = DietType.HERBIVORE,
            dietDetails = "Reaches delicious acacia tree leaves high up 🌿",
            habitat = "African Savannas ☀️",
            funFacts = listOf(
                "Giraffes are the tallest animals on planet Earth!",
                "Their tongue is dark purple/blue and can be 20 inches long to avoid sunburn!",
                "Giraffes only need about 30 minutes of sleep each day, often standing up!"
            ),
            superpower = "Ultra-long neck that lets them see miles across the grasslands.",
            accentColorHex = 0xFFFFA000,
            bgLightHex = 0xFFFFF8E1,
            sizeDescription = "Super Tall! (Up to 19 feet tall)"
        ),
        Animal(
            id = "zebra",
            name = "Zebra",
            emoji = "🦓",
            category = AnimalCategory.SAFARI,
            soundEffect = "Neigh-Bark!",
            soundDescription = "High-pitched Whinny",
            babyName = "Foal",
            diet = DietType.HERBIVORE,
            dietDetails = "Loves sweet grassland pasture 🌾",
            habitat = "Open Plains of Africa 🌍",
            funFacts = listOf(
                "Every zebra has a unique pattern of black and white stripes, just like your fingerprint!",
                "Zebras can sleep while standing up on their four sturdy legs.",
                "Their dazzling stripes confuse buzzing flies so they don't get bitten!"
            ),
            superpower = "Dazzling optical camouflage that confuses predators and flies.",
            accentColorHex = 0xFF455A64,
            bgLightHex = 0xFFECEFF1,
            sizeDescription = "Medium-sized horse cousin (Up to 770 lbs)"
        ),
        Animal(
            id = "monkey",
            name = "Monkey",
            emoji = "🐒",
            category = AnimalCategory.SAFARI,
            soundEffect = "Ooh-Ooh Aah-Aah!",
            soundDescription = "Playful Chattering",
            babyName = "Infant",
            diet = DietType.OMNIVORE,
            dietDetails = "Fruits, sweet bananas, seeds, and insects 🍌",
            habitat = "Tropical Rainforest Canopies 🌴",
            funFacts = listOf(
                "Many monkeys have prehensile tails that can grab tree branches like a fifth hand!",
                "Monkeys groom each other's fur to show love and friendship.",
                "They communicate with facial expressions, chirps, and funny gestures!"
            ),
            superpower = "Acrobatic tree swinging and incredible balance!",
            accentColorHex = 0xFF8D6E63,
            bgLightHex = 0xFFEFEBE9,
            sizeDescription = "Quick & agile climber"
        ),
        Animal(
            id = "cow",
            name = "Cow",
            emoji = "🐮",
            category = AnimalCategory.FARM,
            soundEffect = "Moooo Moooo!",
            soundDescription = "Deep Friendly Moo",
            babyName = "Calf",
            diet = DietType.HERBIVORE,
            dietDetails = "Loves fresh pasture grass and golden hay 🌾",
            habitat = "Green Farm Meadows 🚜",
            funFacts = listOf(
                "Cows have four stomach chambers to help them digest tough, crunchy grass.",
                "Cows have best friends and get happier when they spend time grazing together!",
                "A cow has almost 360-degree panoramic vision to watch for danger."
            ),
            superpower = "Super sense of smell! They can smell scents 6 miles away.",
            accentColorHex = 0xFF4CAF50,
            bgLightHex = 0xFFE8F5E9,
            sizeDescription = "Big & gentle (Up to 1,500 lbs)"
        ),
        Animal(
            id = "horse",
            name = "Horse",
            emoji = "🐴",
            category = AnimalCategory.FARM,
            soundEffect = "Neighhh Whinny!",
            soundDescription = "Energetic Galloping Neigh",
            babyName = "Foal",
            diet = DietType.HERBIVORE,
            dietDetails = "Crisp apples, sweet carrots, and hay 🥕🍏",
            habitat = "Barns, Ranches, and Green Pastures 🏡",
            funFacts = listOf(
                "Horses can run within just a few hours after being born!",
                "They have the largest eyes of any land mammal, giving great vision.",
                "Horses communicate their mood by twitching and angling their ears."
            ),
            superpower = "Blazing sprint speed! Can gallop over 40 miles per hour.",
            accentColorHex = 0xFF795548,
            bgLightHex = 0xFFD7CCC8,
            sizeDescription = "Graceful, muscular, and speedy"
        ),
        Animal(
            id = "sheep",
            name = "Sheep",
            emoji = "🐑",
            category = AnimalCategory.FARM,
            soundEffect = "Baaa Baaa!",
            soundDescription = "Fluffy Bleat",
            babyName = "Lamb",
            diet = DietType.HERBIVORE,
            dietDetails = "Climbing hills to graze on grass and clover ☘️",
            habitat = "Rolling Hills and Sunny Pastures 🏞️",
            funFacts = listOf(
                "Sheep grow a warm, cozy fleece that can be sheared into soft wool for winter sweaters!",
                "Sheep have excellent memory and can recognize up to 50 individual face shapes.",
                "When baby lambs are happy, they love to do little jumping bounces in the air!"
            ),
            superpower = "Fluffy natural weatherproof wool coat that stays warm in winter.",
            accentColorHex = 0xFF78909C,
            bgLightHex = 0xFFECEFF1,
            sizeDescription = "Fluffy, soft, and gentle"
        ),
        Animal(
            id = "pig",
            name = "Pig",
            emoji = "🐷",
            category = AnimalCategory.FARM,
            soundEffect = "Oink Oink Snort!",
            soundDescription = "Happy Snorting Oink",
            babyName = "Piglet",
            diet = DietType.OMNIVORE,
            dietDetails = "Apples, corn, melons, and leafy greens 🌽🍉",
            habitat = "Farmyard Mud Wallows and Barns 🏡",
            funFacts = listOf(
                "Pigs roll in cool mud because they cannot sweat, so mud acts like sunblock!",
                "Pigs are among the smartest animals in the world, smarter than dogs at puzzles!",
                "Mother pigs sing soft lullabies to their little piglets while nursing!"
            ),
            superpower = "Super smart brain and a strong sniffer nose that can dig for truffles.",
            accentColorHex = 0xFFEC407A,
            bgLightHex = 0xFFFCE4EC,
            sizeDescription = "Chubby and curious"
        ),
        Animal(
            id = "duck",
            name = "Duck",
            emoji = "🦆",
            category = AnimalCategory.FARM,
            soundEffect = "Quack Quack Quack!",
            soundDescription = "Bouncy Pond Quack",
            babyName = "Duckling",
            diet = DietType.OMNIVORE,
            dietDetails = "Duckweed, pond insects, and seeds 💦",
            habitat = "Farm Ponds, Lakes, and Rivers 🦆",
            funFacts = listOf(
                "Duck feathers are totally waterproof because of special natural oils!",
                "Baby ducklings can swim shortly after hatching and waddle in a line behind mom.",
                "Ducks have webbed feet that paddle like scuba swim fins under the water!"
            ),
            superpower = "Triple-traveler! Can fly in the air, waddle on land, and paddle in water.",
            accentColorHex = 0xFF00897B,
            bgLightHex = 0xFFE0F2F1,
            sizeDescription = "Small, buoyant swimmer"
        ),
        Animal(
            id = "rooster",
            name = "Rooster",
            emoji = "🐓",
            category = AnimalCategory.FARM,
            soundEffect = "Cock-a-doodle-doo!",
            soundDescription = "Sunrise Wakeup Call",
            babyName = "Chick",
            diet = DietType.OMNIVORE,
            dietDetails = "Grain, sunflower seeds, and berries 🌾",
            habitat = "Sunny Farm Coops ☀️",
            funFacts = listOf(
                "Roosters crow at sunrise to say hello and tell the flock that morning is here!",
                "Roosters have colorful, shiny feathers and a bright red comb on top of their head.",
                "They protect the hens and chicks by constantly scouting the farmyard."
            ),
            superpower = "Internal biological clock that wakes up the farm at dawn.",
            accentColorHex = 0xFFE53935,
            bgLightHex = 0xFFFFEBEE,
            sizeDescription = "Proud, colorful feathered bird"
        ),
        Animal(
            id = "dolphin",
            name = "Dolphin",
            emoji = "🐬",
            category = AnimalCategory.OCEAN,
            soundEffect = "Click-Click Whistle!",
            soundDescription = "Playful Sonar Chirps",
            babyName = "Calf",
            diet = DietType.CARNIVORE,
            dietDetails = "Small fish and tasty squid 🐟",
            habitat = "Warm Oceans & Coral Reefs 🌊",
            funFacts = listOf(
                "Dolphins are not fish; they are mammals that breathe air through a blowhole!",
                "They use echolocation (bouncing sound waves) to 'see' through deep, dark waters.",
                "Dolphins are super playful and love surfing the bow waves made by boats!"
            ),
            superpower = "Echolocation radar and acrobat leaps out of the water.",
            accentColorHex = 0xFF0288D1,
            bgLightHex = 0xFFE1F5FE,
            sizeDescription = "Sleek, hydrodynamic swimmer (Up to 500 lbs)"
        ),
        Animal(
            id = "whale",
            name = "Blue Whale",
            emoji = "🐋",
            category = AnimalCategory.OCEAN,
            soundEffect = "Whoooo-Song!",
            soundDescription = "Deep Ocean Melody",
            babyName = "Calf",
            diet = DietType.CARNIVORE,
            dietDetails = "Tiny ocean shrimp called krill 🦐",
            habitat = "Deep Oceans Worldwide 🌊",
            funFacts = listOf(
                "The Blue Whale is the biggest creature that has EVER lived on Earth, even bigger than T-Rex!",
                "A blue whale's heart is as large as a small car!",
                "Their deep songs can travel hundreds of miles through the ocean to talk to friends."
            ),
            superpower = "Colossal strength and whale songs that travel across oceans.",
            accentColorHex = 0xFF1976D2,
            bgLightHex = 0xFFE3F2FD,
            sizeDescription = "The Largest Animal in History (Up to 300,000 lbs!)"
        ),
        Animal(
            id = "turtle",
            name = "Sea Turtle",
            emoji = "🐢",
            category = AnimalCategory.OCEAN,
            soundEffect = "Splash-Glub!",
            soundDescription = "Gentle Bubble Paddle",
            babyName = "Hatchling",
            diet = DietType.OMNIVORE,
            dietDetails = "Seagrass, soft sponges, and jellyfish 🌿",
            habitat = "Tropical Coral Reefs & Sandy Beaches 🏝️",
            funFacts = listOf(
                "Sea turtles have lived on Earth for more than 100 million years!",
                "Their strong shell is like a built-in protective armor shield.",
                "They can hold their breath underwater for up to 5 hours while resting!"
            ),
            superpower = "Natural magnetic GPS! They always find their way back to their birth beach.",
            accentColorHex = 0xFF2E7D32,
            bgLightHex = 0xFFE8F5E9,
            sizeDescription = "Ancient shelled ocean glider"
        ),
        Animal(
            id = "octopus",
            name = "Octopus",
            emoji = "🐙",
            category = AnimalCategory.OCEAN,
            soundEffect = "Swish-Squirt!",
            soundDescription = "Inky Jet Bubble",
            babyName = "Larva",
            diet = DietType.CARNIVORE,
            dietDetails = "Crabs, lobsters, and shellfish 🦀",
            habitat = "Deep Ocean Caves & Coral Hideouts 🪸",
            funFacts = listOf(
                "An octopus has 8 flexible arms lined with hundreds of sensitive suction cups!",
                "They have 3 hearts and their blood is colored blue!",
                "They can change their skin color and texture in the blink of an eye to camouflage!"
            ),
            superpower = "Instant shape-shifting camouflage and cloud ink escape puff.",
            accentColorHex = 0xFF8E24AA,
            bgLightHex = 0xFFF3E5F5,
            sizeDescription = "Flexible, boneless underwater wizard"
        ),
        Animal(
            id = "clownfish",
            name = "Clownfish",
            emoji = "🐠",
            category = AnimalCategory.OCEAN,
            soundEffect = "Pop-Pop-Glub!",
            soundDescription = "Tiny Bubble Pop",
            babyName = "Fry",
            diet = DietType.OMNIVORE,
            dietDetails = "Tiny algae and sea plankton 🫧",
            habitat = "Coral Sea Anemones 🪸",
            funFacts = listOf(
                "Clownfish live safely inside stinging sea anemones because they have a special mucus coat!",
                "They are bright orange with crisp white stripes, just like finding Nemo!",
                "They keep their sea anemone home clean by nibbling away algae."
            ),
            superpower = "Immunity to jellyfish and anemone stings!",
            accentColorHex = 0xFFFF5722,
            bgLightHex = 0xFFFBE9E7,
            sizeDescription = "Cute, palm-sized coral swimmer"
        ),
        Animal(
            id = "penguin",
            name = "Penguin",
            emoji = "🐧",
            category = AnimalCategory.OCEAN,
            soundEffect = "Honk-Squawk!",
            soundDescription = "Cheerful Ice Squawk",
            babyName = "Chick",
            diet = DietType.CARNIVORE,
            dietDetails = "Small silver fish, krill, and squid 🐟",
            habitat = "Antarctic Ice & Polar Shores ❄️",
            funFacts = listOf(
                "Penguins are birds, but instead of flying in the air, they fly underwater like torpedoes!",
                "They wear a natural 'tuxedo' feather suit that keeps them cozy in freezing blizzards.",
                "Penguins slide on their bellies across the slick ice—a move called 'tobogganing'!"
            ),
            superpower = "Super deep-sea cold diver and funny belly tobogganer.",
            accentColorHex = 0xFF37474F,
            bgLightHex = 0xFFECEFF1,
            sizeDescription = "Chubby, waddling tuxedo bird"
        ),
        Animal(
            id = "eagle",
            name = "Bald Eagle",
            emoji = "🦅",
            category = AnimalCategory.BIRDS,
            soundEffect = "Screee-Kreee!",
            soundDescription = "Piercing Sky Cry",
            babyName = "Eaglet",
            diet = DietType.CARNIVORE,
            dietDetails = "Fresh fish scooped from mountain lakes 🐟",
            habitat = "Tall Pines near Lakes and Mountains 🌲",
            funFacts = listOf(
                "Bald Eagles have eyesight 4 to 8 times sharper than humans; they spot fish from miles away!",
                "Their wings can stretch up to 7 feet wide from tip to tip!",
                "They build giant twig nests high in tree tops that can weigh over a ton!"
            ),
            superpower = "Telescopic sky vision and razor-sharp fish talons.",
            accentColorHex = 0xFF5D4037,
            bgLightHex = 0xFFEFEBE9,
            sizeDescription = "Majestic raptor with 7-foot wingspan"
        ),
        Animal(
            id = "parrot",
            name = "Parrot",
            emoji = "🦜",
            category = AnimalCategory.BIRDS,
            soundEffect = "Squawk! Hello Pal!",
            soundDescription = "Mimicking Talkative Chirp",
            babyName = "Chick",
            diet = DietType.HERBIVORE,
            dietDetails = "Tropical fruits, crunchy nuts, and sweet seeds 🥭",
            habitat = "Lush Tropical Rainforests 🌴",
            funFacts = listOf(
                "Parrots are so clever they can learn to speak human words and mimic funny sounds!",
                "They use their strong, curved beak like an extra foot to climb jungle vines.",
                "Parrots have gorgeous rainbow feathers in vivid red, blue, green, and yellow!"
            ),
            superpower = "Vocal mimicry! Can repeat words, tunes, and laughter.",
            accentColorHex = 0xFF00C853,
            bgLightHex = 0xFFE8F8F5,
            sizeDescription = "Bright rainbow flyer"
        ),
        Animal(
            id = "owl",
            name = "Barn Owl",
            emoji = "🦉",
            category = AnimalCategory.BIRDS,
            soundEffect = "Hoooot Hoooot!",
            soundDescription = "Mysterious Night Hoot",
            babyName = "Owlet",
            diet = DietType.CARNIVORE,
            dietDetails = "Garden mice, beetles, and crickets 🦗",
            habitat = "Quiet Barns and Hollow Trees 🌙",
            funFacts = listOf(
                "Owls can turn their heads almost all the way around—270 degrees!",
                "Their special feathered wings let them fly in total, whisper-quiet silence.",
                "Owls have incredible night vision to hunt under the shining stars."
            ),
            superpower = "100% Silent flight and 270-degree swiveling head!",
            accentColorHex = 0xFF6D4C41,
            bgLightHex = 0xFFFBE9E7,
            sizeDescription = "Night watcher with heart-shaped face"
        ),
        Animal(
            id = "bear",
            name = "Brown Bear",
            emoji = "🐻",
            category = AnimalCategory.FOREST,
            soundEffect = "Grrrr-Growl!",
            soundDescription = "Mighty Deep Growl",
            babyName = "Cub",
            diet = DietType.OMNIVORE,
            dietDetails = "Wild berries, honeycomb, roots, and river salmon 🍯🐟",
            habitat = "Deep Mountain Forests and Rivers 🌲",
            funFacts = listOf(
                "Brown bears hibernate in cozy mountain dens throughout the chilly winter.",
                "Bears love sweet treats like ripe blueberries and golden wild honey!",
                "Mother bears teach their tiny cubs how to fish and find berries in spring."
            ),
            superpower = "Supreme sense of smell, 7 times better than a bloodhound dog!",
            accentColorHex = 0xFF4E342E,
            bgLightHex = 0xFFD7CCC8,
            sizeDescription = "Furry giant of the woods (Up to 900 lbs)"
        ),
        Animal(
            id = "fox",
            name = "Red Fox",
            emoji = "🦊",
            category = AnimalCategory.FOREST,
            soundEffect = "Ring-ding-ding-bark!",
            soundDescription = "Playful High Bark",
            babyName = "Kit",
            diet = DietType.OMNIVORE,
            dietDetails = "Forest berries, mice, and crunchy acorns 🍓",
            habitat = "Woodlands, Meadows, and Snowy Hills 🍂",
            funFacts = listOf(
                "A fox has a big bushy tail called a 'brush' that wraps around its nose like a blanket!",
                "Foxes can hear a tiny mouse squeak beneath 3 feet of snow!",
                "They use the Earth's magnetic field to help aim their playful pounces."
            ),
            superpower = "Acrobatic snow pounce and super hearing.",
            accentColorHex = 0xFFFF6D00,
            bgLightHex = 0xFFFFF3E0,
            sizeDescription = "Clever, bushy-tailed forest sprite"
        ),
        Animal(
            id = "rabbit",
            name = "Bunny Rabbit",
            emoji = "🐰",
            category = AnimalCategory.FOREST,
            soundEffect = "Thump Thump Thump!",
            soundDescription = "Foot Thump Alert",
            babyName = "Bunny",
            diet = DietType.HERBIVORE,
            dietDetails = "Sweet clover, crunchy carrots, and fresh timothy hay 🥕",
            habitat = "Grassy Burrows and Forest Clearings 🌿",
            funFacts = listOf(
                "When bunnies are super happy, they do a joyful twisting leap called a 'Binky'!",
                "A rabbit's long ears can turn independently to listen in all directions.",
                "Their teeth never stop growing, which is why they love to munch crunchy vegetables!"
            ),
            superpower = "Rocket-speed zig-zag hopping and the famous happy 'Binky' jump!",
            accentColorHex = 0xFFAB47BC,
            bgLightHex = 0xFFF3E5F5,
            sizeDescription = "Soft, twitchy-nosed bundle of joy"
        ),
        Animal(
            id = "dog",
            name = "Puppy Dog",
            emoji = "🐶",
            category = AnimalCategory.PETS,
            soundEffect = "Woof Woof Arf!",
            soundDescription = "Happy Tail-Wagging Bark",
            babyName = "Puppy",
            diet = DietType.OMNIVORE,
            dietDetails = "Healthy puppy kibble, chicken, and peanut butter treats 🦴",
            habitat = "Cozy Loving Homes and Backyards 🏡",
            funFacts = listOf(
                "Dogs wag their tails to express excitement, friendship, and love!",
                "A dog's nose print is completely unique, just like human fingerprints.",
                "Puppies dream when they sleep—you can see their paws twitch as they chase balls!"
            ),
            superpower = "Best Friend loyalty and unconditional happy hugs!",
            accentColorHex = 0xFFF57C00,
            bgLightHex = 0xFFFFF3E0,
            sizeDescription = "Friendly furry cuddle buddy"
        ),
        Animal(
            id = "cat",
            name = "Kitty Cat",
            emoji = "🐱",
            category = AnimalCategory.PETS,
            soundEffect = "Meow Meow Purrr!",
            soundDescription = "Soothing Gentle Purr",
            babyName = "Kitten",
            diet = DietType.CARNIVORE,
            dietDetails = "Tasty salmon snacks and milk treats 🐟",
            habitat = "Sunny Windowsills and Cozy Sofas 🛋️",
            funFacts = listOf(
                "Cats purr when they are happy, relaxed, or feeling loved by their humans.",
                "Cats have flexible spines and always tend to land on their soft, padded feet!",
                "Their whiskers act like tiny radar sensors to measure spaces in the dark."
            ),
            superpower = "Ninja agility, night vision, and soothing healing purrs.",
            accentColorHex = 0xFF00ACC1,
            bgLightHex = 0xFFE0F7FA,
            sizeDescription = "Curious, soft, acrobatic jumper"
        )
    )

    fun getAnimalById(id: String): Animal? = animals.find { it.id == id }

    fun getRandomAnimal(): Animal = animals.random()

    fun getCategoryAnimals(category: AnimalCategory): List<Animal> {
        return if (category == AnimalCategory.ALL) {
            animals
        } else {
            animals.filter { it.category == category }
        }
    }
}
