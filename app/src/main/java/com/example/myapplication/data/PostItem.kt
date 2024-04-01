package com.example.myapplication.data

data class PostItem(
    val username: String,
    val avatarUrl: String,
    val bookTitle: String,
    val bookGenre: String,
    val bookAuthor: String,
    val bookText: String,
    val videoUrl: String
)

object DataPost {
    val textList = listOf(
        "А черное небо чертят огнистыми полосками падающие звезды. Долго глядишь в его темно-синюю глубину, переполненную созвездиями, пока не поплывет земля под ногами. Тогда встрепенешься и, пряча руки в рукава, быстро побежишь по аллее к дому... Как холодно, росисто и как хорошо жить на свете!",
        "Казалось, в этом теле совсем не было души, или она у него была, но вовсе не там, где следует, а, как у бессмертного Кощея, где-то за горами и закрыта такою толстою скорлупою, что все, что ни ворочалось на дне ее, не производило решительно никакого потрясения на поверхности.",
        "Ритор отошел в сторону и старался ползком нащупать дорогу, но руки его попадали только в лисьи норы. Везде была одна степь, по которой, казалось, никто не ездил. ",
        "Утро наступало. Лизавета Ивановна погасила догорающую свечу: бледный свет озарил ее комнату. Она отерла заплаканные глаза и подняла их на Германна: он сидел на окошке, сложа руки и грозно нахмурясь.",
        "Петр глянул в сторону, куда указывал барин. Несколько телег, запряженных разнузданными лошадьми, шибко катились по узкому проселку. В каждой телеге сидело по одному, много по два мужика в тулупах нараспашку.",
        "Лодка, вспенивая воду, обогнула скалу. И тут они увидели дом. Южная сторона острова в отличие от северной отлого спускалась к морю. Дом расположился на южном склоне – низкий, квадратный, построенный в современном стиле, с огромными закругленными окнами.",
        "Стоя в темноте у открытого окна, он набрал полную грудь воздуха и изо всех сил дунул. Уличные фонари мигом погасли, точно свечки на черном именинном пироге. Дуглас дунул еще и еще, и в небе начали гаснуть звезды.",
        "Мисси высунулась из-за букета нарциссов, стоявшего в хрустальной вазе, и бросила взгляд на усохшего, как мумия, супруга. У обоих затряслись руки, оба это заметили.",
        "Ослушаться приказа советник побоялся и лишь молчаливо склонил голову. Иландар в последний раз одарил его испепеляющим взглядом и продолжил путь. Его трясло от ужаса и ярости.",
        "Ослушаться приказа советник побоялся и лишь молчаливо склонил голову. Иландар в последний раз одарил его испепеляющим взглядом и продолжил путь. Его трясло от ужаса и ярости.",
    )

    val videoUrlList = listOf(
        "https://static.videezy.com/system/resources/previews/000/054/572/original/R-10.mp4",
        "https://videocdn.cdnpk.net/joy/content/video/free/video0485/large_preview/_import_61b8659e340699.39086200.mp4?filename=1118583_4k_health-care_fear_3840x2160.mp4",
        "https://videocdn.cdnpk.net/joy/content/video/free/video0485/large_preview/_import_61b8640054c5f2.85372532.mp4?filename=1118575_4k_health-care_fear_3840x2160.mp4",
        "https://videocdn.cdnpk.net/joy/content/video/free/video0453/large_preview/_115__import.mp4?filename=1102360_1080p_animation_outbreak_1920x1080.mp4",
        "https://videocdn.cdnpk.net/joy/content/video/free/video0485/large_preview/_import_61c09f0023ec76.61099154.mp4?filename=1118729_4k_imagination_serenity_3840x2160.mp4",
        "https://assets.mixkit.co/videos/preview/mixkit-wild-horses-drinking-water-in-a-lake-35202-large.mp4",
        "https://assets.mixkit.co/videos/preview/mixkit-rocky-surface-of-a-strange-planet-30615-large.mp4",
        "https://assets.mixkit.co/videos/preview/mixkit-businessmen-walking-through-a-door-in-the-desert-35182-large.mp4",
        "https://assets.mixkit.co/videos/preview/mixkit-three-funny-multicoloured-men-dancing-to-upbeat-music-44877-large.mp4",
        "https://assets.mixkit.co/videos/preview/mixkit-airplane-racing-with-a-sports-car-35596-large.mp4",
        "https://assets.mixkit.co/videos/preview/mixkit-navigating-the-river-of-a-luminous-virtual-jungle-31518-large.mp4",
        "https://assets.mixkit.co/videos/preview/mixkit-church-surrounded-by-trees-on-a-sunny-day-33935-large.mp4"
    )
}
