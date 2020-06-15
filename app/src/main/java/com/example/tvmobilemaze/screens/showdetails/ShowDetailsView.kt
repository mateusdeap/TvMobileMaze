package com.example.tvmobilemaze.screens.showdetails

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import com.example.tvmobilemaze.Embedded
import com.example.tvmobilemaze.R
import com.example.tvmobilemaze.ShowDetailsQueryResult
import com.example.tvmobilemaze.screens.common.BaseObservableView
import com.squareup.picasso.Picasso

class ShowDetailsView(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup?,
    private val menuInflater: MenuInflater
) : IShowDetailsView, BaseObservableView<IShowDetailsView.ShowDetailsListener>() {

    override val rootView: View = layoutInflater.inflate(R.layout.activity_show_details, parent, false)

    private val posterImageView: ImageView = findViewById(R.id.show_details_poster)
    private val showNameTextView: TextView = findViewById(R.id.show_details_name)
    private val showScheduleTextView: TextView = findViewById(R.id.show_details_schedule)
    private val showGenresTextView: TextView = findViewById(R.id.show_details_genres)
    private val showSummaryTextView: TextView = findViewById(R.id.show_details_summary)
    private val seasonsExpandableList: ExpandableListView = findViewById(R.id.show_details_seasons_expandable_list)

    private val seasonExpandableListAdapter: SeasonExpandableListAdapter = SeasonExpandableListAdapter(layoutInflater)

    init {
        seasonsExpandableList.setAdapter(seasonExpandableListAdapter)
    }

    override fun exhibitShowInfo(showInfo: ShowDetailsQueryResult) {
        bindPoster(showInfo)
        bindGeneralInfo(showInfo)
        populateEpisodeList(showInfo._embedded)
    }

    override fun showError(error: Throwable?) {
        TODO("Not yet implemented")
    }

    private fun bindPoster(showInfo: ShowDetailsQueryResult) {
        Picasso.get()
            .load(showInfo.image?.medium)
            .placeholder(R.drawable.placeholder_128dp)
            .error(R.drawable.placeholder_128dp)
            .into(posterImageView)
    }

    private fun bindGeneralInfo(showInfo: ShowDetailsQueryResult) {
        showNameTextView.text = showInfo.name
        showScheduleTextView.text = showInfo.schedule.toString()
        showGenresTextView.text = showInfo.genres.toString()
        showSummaryTextView.text = showInfo.summary
    }

    private fun populateEpisodeList(embeddedInfo: Embedded) {
        val seasons = embeddedInfo.seasons
        val episodes = embeddedInfo.episodes
        seasonExpandableListAdapter.bindShowInfo(seasons, episodes)
    }
}