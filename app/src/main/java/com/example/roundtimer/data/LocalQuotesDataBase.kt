package com.example.roundtimer.data

import com.example.roundtimer.domain.Quote
import com.example.roundtimer.presentation.round_screen.QuotesDatabase

object LocalQuotesDataBase : QuotesDatabase {
    private val quotes: List<Quote> = listOf(
        Quote(1, "You can never understand a person unless you can understand what makes them angry.", "One Punch Man"),
        Quote(2, "What once was treasure can become trash.", "Hunter x Hunter"),
        Quote(3, "In victory or defeat it will be hell.", "Hunter x Hunter"),
        Quote(4, "Stand for something or fall for anything.", ""),
        Quote(5, "No tree, it is said, can grow to heaven. Unless its roots reach down to hell.", "Carl Jung"),
        Quote(6, "Normality is a paved road: It's comfortable to walk, but no flowers grow on it.", "Vincent Van Gogh"),
        Quote(7, "Luck Is What Happens When Preparation Meets Opportunity.", "Seneca"),
        Quote(8, "Every body on mount Everest was once a very determined individual; maybe calm down.", ""),
        Quote(9, "Know that feelings are not facts.", ""),
        Quote(10, "If you don't make time for your wellness you will be forced to make time for your illness.", ""),
        Quote(11, "Weak people revenge; Strong people forgive; Intelligent people ignore.", "Albert Einstein"),
        Quote(12, "If you don't sacrifice for what you want what you want becomes the sacrifice.", ""),
        Quote(13, "Don't cry because it's over, smile because it happened.", "Miura Sensei"),
        Quote(14, "When you base your expectations only on what you see, you blind yourself to the possibilities of a new reality.", "Guru Laghima / Zaheer"),
        Quote(15, "It's not about managing your emotions, it's about managing your reaction to your emotions.", "Yung Pueblo"),
        Quote(16, "Knowing what you're supposed to do is easy. Doing what you're supposed to do is one of the hardest things in life.", ""),
        Quote(17, "The happiness of your life depends on the quality of your thoughts.", "Marcus Aurelius"),
        Quote(18, "The way you need love is the way you lacked love, and the way you give love is the way you were given love.", ""),
        Quote(19, "To see a man beaten not by a better opponent, but by himself is a tragedy.", "Cu D'Amato"),
        Quote(20, "The only limit to our realization of tomorrow is our doubts of today.", "Franklin D. Roosevelt"),
        Quote(21, "If you gaze long enough into an abyss, the abyss will gaze back into you.", "Friedrich Nietzsche"),
        Quote(22, "The only way to do great work is to love what you do.", "Mahatma Gandhi"),
        Quote(23, "In the middle of difficulty lies opportunity.", "Albert Einstein"),
        Quote(24, "It does not matter how slowly you go as long as you do not stop.", "Confucius"),
        Quote(25, "The greatest glory in living lies not in never falling, but in rising every time we fall.", "Nelson Mandela"),
        Quote(26, "He who has a why to live can bear almost any how.", "Friedrich Nietzsche"),
        Quote(27, "In order to carry a positive action, we must develop here a positive vision.", "Dalai Lama"),
        Quote(28, "Everything you can imagine is real.", "Pablo Picasso"),
        Quote(29, "What we think, we become.", "Buddha"),
        Quote(30, "The wound is the place where the Light enters you.", "Rumi"),
        Quote(31, "Your most unhappy customers are your greatest source of learning.", "Khalil Gibran"),
        Quote(32, "You must never be fearful about what you are doing when it is right.", "Rosa Parks"),
        Quote(33, "You will face many defeats in life, but never let yourself be defeated.", "Maya Angelou"),
        Quote(34, "One child, one teacher, one pen, and one book can change the world.", "Malala Yousafzai"),
        Quote(35, "At the end of the day, we can endure much more than we think we can.", "Frida Kahlo"),
        Quote(36, "We teach girls to shrink themselves, to make themselves smaller.", "Chimamanda Ngozi Adichie"),
        Quote(37, "It's the little things citizens do. That's what will make the difference. My little thing is planting trees.", "Wangari Maathai"),
        Quote(38, "What matters in life is not what happens to you but what you remember and how you remember it.", "Gabriel Garcia Marquez"),
        Quote(39, "Some of us think holding on makes us strong, but sometimes it is letting go.", "Hermann Hesse"),
        Quote(40, "The worst danger that a man faces is not the danger of his enemies, but the indifference of his friends.", "Bertolt Brecht"),
        Quote(41, "The only real prison is fear, and the only real freedom is freedom from fear.", "Aung San Suu Kyi"),
        Quote(42, "An expert is a person who has made all the mistakes that can be made in a very narrow field.", "Niels Bohr"),
        Quote(43, "The world is not as we want it to be. It is as we make it.", "Yoko Ono"),
        Quote(44, "The most important thing is to be in your experience, not to be stuck in your mind.", "Sadhguru"),
        Quote(45, "Everyone thinks of changing the world, but no one thinks of changing himself.", "Leo Tolstoy"),
        Quote(46, "A game is a series of interesting choices.", "Hideo Kojima"),
        Quote(47, "The moment you stop worrying is the moment you start winning.", "Sofia Reyes"),
        Quote(48, "You can cut all the flowers but you cannot keep spring from coming.", "Pablo Neruda"),
        Quote(49, "Be yourself; everyone else is already taken.", "Oscar Wilde"),
        Quote(50, "To live is the rarest thing in the world. Most people exist, that is all.", "Oscar Wilde")
    )

    override fun getRandomQuote(): Quote {
        return quotes.random()
    }
}
