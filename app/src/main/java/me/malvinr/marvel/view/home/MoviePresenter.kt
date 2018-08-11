package me.malvinr.marvel.view.home

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.malvinr.marvel.BuildConfig
import me.malvinr.marvel.model.Movie
import org.jsoup.Jsoup

class MoviePresenter(val view: MovieView) {

    fun getMarvelCinematicUniverseLists() {
        view.onProgress()

        val movieList = arrayListOf<Movie>()

        Flowable.fromCallable {
            val connection = Jsoup.connect(BuildConfig.BASE_URL)
            connection.userAgent("Mozilla/5.0")
            connection.timeout(10 * 1000)

            val document = connection.get()

            val lists = document.select("div.content-grid")
                    .select("div.grid-card").select("a.lob__link")

            for (list in lists) {
                val bodyContainer = list.select("div.card-body")
                val title = bodyContainer.select("p.card-body__headline").text()
                val releasedDate = bodyContainer.select("p.card-footer__secondary-text").text()
                val image = list.select("img").first().absUrl("src")

                movieList.add(Movie(title, releasedDate, image))
            }
        }
                .subscribeOn(Schedulers.io())
                /*.doOnSubscribe { Log.d("LOADING : ", "lagi loading") }
                .doOnError { Log.d("LOADING : ", "udah loading") }
                .doOnComplete { Log.d("LOADING : ", "udah loading") }*/
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.onSuccess(movieList)
                        },
                        {
                            view.onError(it.localizedMessage)
                        })
    }

}