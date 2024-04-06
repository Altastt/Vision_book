package com.example.visionbook.data

data class BooksItem(
    var id: String,
    var name: String,
    var url: String,
    var author: String,
    var title: String,
    var genre: String
)

object DataBooksScreen{
    val imageList = listOf(
        "https://i.pinimg.com/564x/03/fb/59/03fb5935622963540a802712f8313304.jpg",
        "https://i.pinimg.com/564x/f8/e8/8b/f8e88b0bc000e264c79e5ceb28a68e78.jpg",
        "https://i.pinimg.com/564x/3e/98/1a/3e981ab9dcfedcf8f73b1366b6cc4a4b.jpg",
        "https://i.pinimg.com/564x/cc/6a/53/cc6a538523054a820cd14bf9808d5b09.jpg",
        "https://i.pinimg.com/564x/4e/15/ad/4e15ad09fcd43aaed6904fbbcf786c44.jpg",
        "https://i.pinimg.com/564x/f4/09/c5/f409c5276e42c7c15204bce643948b0b.jpg",
        "https://i.pinimg.com/564x/75/f6/dc/75f6dcb904a47f31aebc14046f4b2dd9.jpg",
        "https://i.pinimg.com/564x/11/44/23/11442394eda50dcc6d8dc7fa4eed8807.jpg",
        "https://i.pinimg.com/564x/e7/09/ec/e709ecf5f772dcd78f01dcad16111a32.jpg",
        "https://i.pinimg.com/564x/fb/e9/72/fbe9724232c791a697d48458854bee03.jpg"
    )

    val coverList = listOf(
        "https://iv-obdu.ru/images/galery/2020/chz_vbun-05.jpg",
        "https://cdn1.ozone.ru/s3/multimedia-p/6046527865.jpg",
        "https://cv9.litres.ru/pub/c/cover_max1500/26380699.jpg",
        "https://lotsofbooks.be/upload/iblock/b2a/b2a2f29f83aef8aa380fb6ca624556c2.jpg",
        "https://polinka.net/wp-content/uploads/2017/09/2017-09-19_232007.jpg",
        "https://cdn1.ozone.ru/multimedia/c750/1014025948.jpg",
        "https://content.img-gorod.ru/nomenclature/24/798/2479814.jpg",
        "https://www.litlib.net/bk/7440/08-2009/cover.jpg",
        "https://www.1c-interes.ru/images/2024/02/Bezlikayakoroleva_big.jpg",
        "https://ia802807.us.archive.org/34/items/IMG0020_201809/IMG_0016.jpg"
    )
    val authorsList = listOf(
        "Агата Кристи",
        "Лев Толстой",
        "Александр Пушкин",
        "Карен Коулс",
        "Пол Линч",
        "Рей Бредбери",
        "Жюль Верн",
        "Иван Бунин",
        "Иван Тургенев",
        "Николай Гоголь",
    )
    val titleList = listOf(
        "Антоновские яблоки",
        "Мертвые души",
        "Вий",
        "Пиковая дама",
        "Отцы и дети",
        "10 негритят",
        "Вино из одуванчиков",
        "Убить полюбовно",
        "Безликая королева",
        "Песнь пророка",
    )
    val genreList = listOf(
        "роман",
        "сказка",
        "фантастика",
        "детектив",
        "драма",
        "ужасы",
        "боевики",
        "приключение",
        "мистика",
        "фэнтези",
    )
}

