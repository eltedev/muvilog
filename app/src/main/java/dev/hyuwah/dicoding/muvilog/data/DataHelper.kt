package dev.hyuwah.dicoding.muvilog.data

import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.model.Movie
import dev.hyuwah.dicoding.muvilog.presentation.model.TVShow

object DataHelper {

    const val IMG_BASE_URL = "https://image.tmdb.org/t/p/"

    fun generateMovieList(): ArrayList<Movie> {
        return arrayListOf(
            Movie(
                "0",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                R.drawable.poster_aquaman,
                "Action, Adventure, Fantasy",
                144,
                "68%"
            ),
            Movie(
                "1",
                "Avenger Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                R.drawable.poster_avengerinfinity,
                "Action, Adventure, Science Fiction",
                149,
                "83%"
            ),
            Movie(
                "2",
                "Glass",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                R.drawable.poster_glass,
                "Thriller, Drama, Science Fiction",
                129,
                "65%"
            ),
            Movie(
                "3",
                "Spider-Man: Into the Spider-Verse",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                R.drawable.poster_spiderman,
                "Action, Adventure, Animation, Comedy, Science Fiction",
                117,
                "84%"
            ),
            Movie(
                "4",
                "Venom",
                "Investigative journalist Eddie Brock attempts a comeback following a scandal, but accidentally becomes the host of Venom, a violent, super powerful alien symbiote. Soon, he must rely on his newfound powers to protect the world from a shadowy organization looking for a symbiote of their own.",
                R.drawable.poster_venom,
                "Action, Science Fiction",
                112,
                "66%"
            ),
            Movie(
                "5",
                "Preman Pensiun",
                "After three years, the business of Muslihat (Epi Kusnandar), who has retired as a thug, has a problem. Sales decline. Muslihat also faces new problems when Safira (Safira Maharani), her only daughter, grows up in adolescence and begins to be visited by boys. A bigger problem: frictions between his former subordinates.",
                R.drawable.poster_preman,
                "Comedy, Drama",
                94,
                "65%"
            ),
            Movie(
                "6",
                "Bumblebee",
                "On the run in the year 1987, Bumblebee finds refuge in a junkyard in a small Californian beach town. Charlie, on the cusp of turning 18 and trying to find her place in the world, discovers Bumblebee, battle-scarred and broken. When Charlie revives him, she quickly learns this is no ordinary yellow VW bug.",
                R.drawable.poster_bumblebee,
                "Action, Adventure, Science Fiction",
                116,
                "65%"
            ),
            Movie(
                "7",
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                R.drawable.poster_creed,
                "Drama",
                130,
                "67%"
            ),
            Movie(
                "8",
                "Robin Hood",
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                R.drawable.poster_robinhood,
                "Thriller, Adventure, Action",
                114,
                "58%"
            ),
            Movie(
                "9",
                "The Mule",
                "Earl Stone, a man in his 80s, is broke, alone, and facing foreclosure of his business when he is offered a job that simply requires him to drive. Easy enough, but, unbeknownst to Earl, he’s just signed on as a drug courier for a Mexican cartel. He does so well that his cargo increases exponentially, and Earl hit the radar of hard-charging DEA agent Colin Bates.",
                R.drawable.poster_themule,
                "Crime, Drama, Thriller",
                117,
                "66%"
            )
        )
    }

    fun generateTvShowList(): ArrayList<TVShow> {
        return arrayListOf(
            TVShow(
                "0",
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                R.drawable.poster_arrow,
                "Crime, Drama, Mystery",
                42,
                "58%",
                "October 10, 2012"
            ),
            TVShow(
                "1",
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                R.drawable.poster_family_guy,
                "Animation, Comedy",
                22,
                "65%",
                "January 31, 1999"
            ),
            TVShow(
                "2",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                R.drawable.poster_flash,
                "Drama, Sci-fi & Fantasy",
                44,
                "67%",
                "October 7, 2014"
            ),
            TVShow(
                "3",
                "Gotham",
                "Before there was Batman, there was GOTHAM.\n\nEveryone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                R.drawable.poster_gotham,
                "Drama, Fantasy, Crime",
                43,
                "69%",
                "September 22, 2014"
            ),
            TVShow(
                "4",
                "NCIS",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                R.drawable.poster_ncis,
                "Action & Adventure, Crime, Drama",
                45,
                "67%",
                "September 23, 2003"
            ),
            TVShow(
                "5",
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                R.drawable.poster_the_walking_dead,
                "Action & Adventure, Drama, Sci-fi & Fantasy",
                42,
                "73%",
                "October 31, 2010"
            ),
            TVShow(
                "6",
                "Supergirl",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                R.drawable.poster_supergirl,
                "Action & Adventure, Drama, Sci-fi",
                42,
                "58%",
                "October 26, 2015"
            ),
            TVShow(
                "7",
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                R.drawable.poster_grey_anatomy,
                "Drama",
                43,
                "63%",
                "March 27, 2005"
            ),
            TVShow(
                "8",
                "The Simpsons",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                R.drawable.poster_the_simpson,
                "Animation, Comedy",
                22,
                "71%",
                "December 17, 1989"
            ),
            TVShow(
                "9",
                "Supernatural",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                R.drawable.poster_supernatural,
                "Drama, Mystery, Sci-fi & Fantasy",
                45,
                "73%",
                "September 13, 2005"
            )
        )
    }

}