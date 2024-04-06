package com.example.visionbook.data

data class ProfileItem (
    var id: String,
    var url: String,
    var nickname: String,
    var followersCount: Int,
    var followingCount: Int
)

object DataProfileScreen {
    val nicknameList = listOf(
        "ShadowBladeX",
        "MysticWhisper",
        "NeonVortex",
        "SilverSphinx",
        "CrimsonFury",
        "LunarPhoenix",
        "ElectricJaguar",
        "AzureDreamer",
        "CelestialWanderer",
        "BlazeSpecter",
        )

    val avatarList = listOf(
        "https://avatarko.ru/img/avatar/8/nyasha_7578.jpg",
        "https://avatarko.ru/img/avatar/6/zhivotnye_yozhik_nyasha_5757.jpg",
        "https://avatarko.ru/img/avatar/25/siluet_luna_vedma_25000.jpg",
        "https://avatarko.ru/img/avatar/20/vorona_19871.jpg",
        "https://avatarko.ru/img/kartinka/10/pokemon_pikachu_provoda_9428.jpg",
        "https://avatarko.ru/img/avatar/26/snegovik_pesok_25590.jpg",
        "https://avatarko.ru/img/avatar/15/multfilm_minion_14689.jpg",
        "https://avatarko.ru/img/avatar/22/drakon_kniga_21735.jpg",
        "https://avatarko.ru/img/avatar/33/zhivotnye_ulitka_32342.jpg",
        "https://avatarko.ru/img/avatar/30/zhivotnye_kot_29654.jpg",
    )
}



