package com.example.tvmobilemaze.screens.showdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.tvmobilemaze.Episode
import com.example.tvmobilemaze.R
import com.example.tvmobilemaze.Season

class SeasonExpandableListAdapter(private val layoutInflater: LayoutInflater) : BaseExpandableListAdapter() {

    private var episodes: List<Episode> = ArrayList()
    private var seasons: List<Season> = ArrayList()
    private var episodesPerSeason: HashMap<Season, List<Episode>> = HashMap()

    fun bindShowInfo(seasons: List<Season>, episodes: List<Episode>) {
        this.episodes = episodes
        this.seasons = seasons
        for (season in seasons) {
            val seasonEpisodes = episodes.filter { it.season == season.number }
            episodesPerSeason[season] = seasonEpisodes
        }
        notifyDataSetChanged()
    }

    override fun getGroup(groupPosition: Int): Any {
        return seasons[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val newConvertView: View =
            convertView ?: layoutInflater.inflate(R.layout.season_item_layout, parent, false)

        val seasonNumberTv: TextView? = newConvertView.findViewById(R.id.season_number)
        seasonNumberTv?.text = (getGroup(groupPosition) as Season).toString()

        return newConvertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return episodesPerSeason.getOrDefault(getGroup(groupPosition) as Season, emptyList()).size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return episodesPerSeason
            .getOrDefault(getGroup(groupPosition) as Season, emptyList())[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return (getGroup(groupPosition) as Season).number.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val newConvertView: View =
            convertView ?: layoutInflater.inflate(R.layout.episode_item_layout, parent, false)

        val episodeNumberAndTitle: TextView? = newConvertView.findViewById(R.id.episode_number_and_title)
        episodeNumberAndTitle?.text = episodesPerSeason
            .getOrDefault(getGroup(groupPosition) as Season, emptyList())[childPosition].toString()

        return newConvertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return episodesPerSeason
            .getOrDefault(getGroup(groupPosition) as Season, emptyList())[childPosition].number.toLong()
    }

    override fun getGroupCount(): Int {
        return seasons.size
    }
}