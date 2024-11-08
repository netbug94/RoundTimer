package com.example.roundtimer.quote.data

import com.example.roundtimer.round_screen.data.QuotesDataSource
import com.example.roundtimer.quote.domain.Quote

@Suppress("SpellCheckingInspection")
object LocalQuotesDataBaseES : QuotesDataSource {
    private val quotes: List<Quote> = listOf(
        Quote(
            1,
            "La vida es aquello que te sucede mientras estás ocupado haciendo otros planes.",
            "John Lennon"
        ),
        Quote(
            2,
            "No llores porque ya se terminó, sonríe porque sucedió.",
            "Gabriel García Márquez"
        ),
        Quote(3, "La felicidad no es algo ya hecho. Viene de tus propias acciones.", "Dalai Lama"),
        Quote(4, "El conocimiento habla, pero la sabiduría escucha.", "Jorge Luis Borges"),
        Quote(5, "No hay camino para la paz, la paz es el camino.", "Mahatma Gandhi"),
        Quote(6, "La libertad no es un fin, sino un medio para un fin.", "Simone de Beauvoir"),
        Quote(7, "La imaginación es más importante que el conocimiento.", "Albert Einstein"),
        Quote(
            8,
            "Solo hay dos formas de vivir tu vida. Una es como si nada fuera un milagro. La otra es como si todo fuera un milagro.",
            "Albert Einstein"
        ),
        Quote(
            9,
            "El verdadero viaje de descubrimiento no consiste en buscar nuevos paisajes, sino en mirar con nuevos ojos.",
            "Marcel Proust"
        ),
        Quote(
            10,
            "La mayor gloria no está en nunca caer, sino en levantarnos cada vez que caemos.",
            "Confucio"
        ),
        Quote(
            11,
            "La creatividad requiere el coraje de desprenderse de las certezas.",
            "Erich Fromm"
        ),
        Quote(
            12,
            "El éxito es aprender a ir de fracaso en fracaso sin desesperarse.",
            "Winston Churchill"
        ),
        Quote(
            13,
            "La vida es realmente sencilla, pero insistimos en hacerla complicada.",
            "Confucio"
        ),
        Quote(14, "La medida del amor es amar sin medida.", "San Agustín"),
        Quote(15, "No importa lo lento que vayas mientras no te detengas.", "Confucio"),
        Quote(
            16,
            "La sabiduría no es un producto de la escolarización, sino del intento de adquirirla durante toda la vida.",
            "Albert Einstein"
        ),
        Quote(17, "El hombre que mueve montañas comienza cargando pequeñas piedras.", "Confucio"),
        Quote(18, "La paz comienza con una sonrisa.", "Madre Teresa de Calcuta"),
        Quote(
            19,
            "El mundo es un libro y aquellos que no viajan leen solo una página.",
            "San Agustín"
        ),
        Quote(
            20,
            "La vida es lo que hacemos de ella, siempre lo ha sido, siempre lo será.",
            "Nelson Mandela"
        ),
        Quote(
            21,
            "La educación es el arma más poderosa que puedes usar para cambiar el mundo.",
            "Nelson Mandela"
        ),
        Quote(22, "La mente es todo. Lo que piensas, te conviertes.", "Buda"),
        Quote(23, "No esperes. El tiempo nunca será justo.", "Napoleón Hill"),
        Quote(
            24,
            "El éxito no es la clave de la felicidad. La felicidad es la clave del éxito.",
            "Albert Schweitzer"
        ),
        Quote(
            25,
            "La vida es una serie de cambios naturales y espontáneos. No los resistas; eso solo crea dolor.",
            "Lao Tzu"
        ),
        Quote(26, "No hay atajos para cualquier lugar que valga la pena ir.", "Beverly Sills"),
        Quote(27, "La perseverancia es la clave del éxito.", "Charles Chaplin"),
        Quote(28, "El conocimiento es poder.", "Francis Bacon"),
        Quote(29, "La mejor manera de predecir el futuro es inventarlo.", "Alan Kay"),
        Quote(
            30,
            "El cambio es la ley de la vida. Y aquellos que solo miran al pasado o al presente, seguramente se perderán el futuro.",
            "John F. Kennedy"
        ),
        Quote(31, "Donde hay amor hay vida.", "Mahatma Gandhi"),
        Quote(
            32,
            "La única limitación en tu realización de mañana serán tus dudas de hoy.",
            "Franklin D. Roosevelt"
        ),
        Quote(
            33,
            "El éxito es la suma de pequeños esfuerzos repetidos día tras día.",
            "Robert Collier"
        ),
        Quote(34, "La vida es una aventura atrevida o no es nada.", "Helen Keller"),
        Quote(
            35,
            "El único lugar donde el éxito viene antes que el trabajo es en el diccionario.",
            "Vidal Sassoon"
        ),
        Quote(36, "La felicidad no es algo hecho. Viene de tus propias acciones.", "Dalai Lama"),
        Quote(37, "No cuentes los días, haz que los días cuenten.", "Muhammad Ali"),
        Quote(38, "La forma de empezar es dejar de hablar y comenzar a actuar.", "Walt Disney"),
        Quote(
            39,
            "La vida es 10% lo que me ocurre y 90% cómo reacciono a ello.",
            "Charles R. Swindoll"
        ),
        Quote(
            40,
            "La grandeza no está en donde no se ha caído nunca, sino en levantarse cada vez que se cae.",
            "Confucio"
        ),
        Quote(41, "La mejor manera de predecir el futuro es creándolo.", "Peter Drucker"),
        Quote(42, "Desear ser de otra manera es desperdiciar como eres.", "Octavio Paz"),
        Quote(
            43,
            "La educación es el pasaporte al futuro, el mañana pertenece a aquellos que se preparan para él hoy.",
            "Malcolm X"
        ),
        Quote(
            44,
            "No hay nada imposible, porque los sueños de ayer son las esperanzas de hoy y pueden convertirse en realidad mañana.",
            "Alexander Graham Bell"
        ),
        Quote(
            45,
            "La única forma de descubrir los límites de lo posible es atreviéndose a ir más allá de ellos en lo imposible.",
            "Arthur C. Clarke"
        ),
        Quote(
            46,
            "La vida no se mide por las veces que respiras, sino por los momentos que te dejan sin aliento.",
            "Maya Angelou"
        ),
        Quote(
            47,
            "El verdadero signo de la inteligencia no es el conocimiento sino la imaginación.",
            "Albert Einstein"
        ),
        Quote(48, "La mayor riqueza es la riqueza del alma.", "Ralph Waldo Emerson"),
        Quote(49, "El propósito de nuestras vidas es ser felices.", "Dalai Lama"),
        Quote(50, "La vida es una flor cuya miel es el amor.", "Victor Hugo")
    )

    override fun getRandomQuote(): Quote {
        return quotes.random()
    }
}
