package dev.hyuwah.dicoding.muvilog


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MainMoviesAdapter(requireContext()) {
            val intent = Intent(requireContext(),MovieDetailActivity::class.java).apply {
                putExtra(MovieDetailActivity.MOVIE_KEY, it)
            }
            startActivity(intent)
        }
        adapter.setMovieList(generateData())
        rv_movie_list.layoutManager = LinearLayoutManager(requireContext())
        rv_movie_list.adapter = adapter
    }

    private fun generateData(): ArrayList<Movie> {
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

}
