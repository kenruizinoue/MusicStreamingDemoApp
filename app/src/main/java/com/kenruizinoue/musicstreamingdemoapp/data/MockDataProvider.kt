package com.kenruizinoue.musicstreamingdemoapp.data

import com.kenruizinoue.musicstreamingdemoapp.R

object MockDataProvider {

    val labelItems = listOf("All", "Pop", "Rock", "Jazz", "Hip Hop", "Classical")
    val trackItems = listOf(
        TrackItemData(1, "Midnight Echoes", "Luna Harmonics", R.drawable.cover1, 15000L), // 15 secs
        TrackItemData(2, "Deep Shadows", "Neon Dreamers", R.drawable.cover2, 45000L), // 45 secs
        TrackItemData(3, "Three Worlds", "The Horizon Band", R.drawable.cover3, 120000L), // 2 min
        TrackItemData(4, "Electric Dreams", "Synthwave Riders", R.drawable.cover4, 180000L), // 3 min
        TrackItemData(5, "Galactic Waves", "Calm Seas", R.drawable.cover5, 75000L), // 1 min 15 sec
        TrackItemData(6, "Starry Night", "Milky Way", R.drawable.cover6, 135000L), // 2 min 15 sec
        TrackItemData(7, "Moon", "Misty Moods", R.drawable.cover7, 90000L), // 1 min 30 sec
        TrackItemData(8, "Glowing Kingdom", "Sandy Melodies", R.drawable.cover8, 180000L), // 3 min
        TrackItemData(9, "Universe Lights", "Urban Vibe", R.drawable.cover9, 75000L), // 1 min 15 sec
        TrackItemData(10, "Forest Whisper", "Nature's Rhythm", R.drawable.cover10, 135000L), // 2 min 15 sec
        TrackItemData(11, "Infinity", "Snowy Echoes", R.drawable.cover11, 195000L), // 3 min 15 sec
        TrackItemData(12, "Soulful Stride", "Heartfelt Harmony", R.drawable.cover12, 225000L), // 3 min 45 sec
        TrackItemData(13, "Morning Dew", "Early Risers", R.drawable.cover13, 45000L), // 45 secs
        TrackItemData(14, "Cosmic Journey", "Star Gazers", R.drawable.cover14, 270000L), // 4 min 30 sec
        TrackItemData(15, "Dance of the Light", "Night Glow", R.drawable.cover15, 15000L) // 15 secs
    )
    val bottomBarItems = listOf(
        BottomBarItemData(1, "Home", R.drawable.ic_home),
        BottomBarItemData(2, "Home", R.drawable.ic_search),
        BottomBarItemData(3, "Home", R.drawable.ic_music_library),
        BottomBarItemData(4, "Home", R.drawable.ic_settings)
    )

    fun getNextTrackById(currentId: Int): TrackItemData? {
        val currentIndex = trackItems.indexOfFirst { it.id == currentId }

        // Return null if the currentId is not found.
        if (currentIndex == -1) return null

        // Use modulo operation to cycle to the first track after the last track.
        return trackItems[(currentIndex + 1) % trackItems.size]
    }

    fun getPreviousTrackById(currentId: Int): TrackItemData? {
        val currentIndex = trackItems.indexOfFirst { it.id == currentId }

        // Return null if the currentId is not found.
        if (currentIndex == -1) return null

        val previousIndex = if (currentIndex == 0) trackItems.lastIndex else currentIndex - 1
        return trackItems[previousIndex]
    }
}
