package com.kenruizinoue.musicstreamingdemoapp.data

import com.kenruizinoue.musicstreamingdemoapp.R

object MockTrackListProvider {

    // Defined a list of tracks with their details.
    val trackList = listOf(
        Track(1, "Midnight Echoes", "Luna Harmonics", R.drawable.cover1, 15000L), // 15 secs
        Track(2, "Deep Shadows", "Neon Dreamers", R.drawable.cover2, 45000L), // 45 secs
        Track(3, "Three Worlds", "The Horizon Band", R.drawable.cover3, 120000L), // 2 min
        Track(4, "Electric Dreams", "Synthwave Riders", R.drawable.cover4, 180000L), // 3 min
        Track(5, "Galactic Waves", "Calm Seas", R.drawable.cover5, 75000L), // 1 min 15 sec
        Track(6, "Starry Night", "Van Gogh's Playlist", R.drawable.cover6, 135000L), // 2 min 15 sec
        Track(7, "Moon", "Misty Moods", R.drawable.cover7, 90000L), // 1 min 30 sec
        Track(8, "Glowing Kingdom", "Sandy Melodies", R.drawable.cover8, 180000L), // 3 min
        Track(9, "Universe Lights", "Urban Vibe", R.drawable.cover9, 75000L), // 1 min 15 sec
        Track(10, "Forest Whisper", "Nature's Rhythm", R.drawable.cover10, 135000L), // 2 min 15 sec
        Track(11, "Infinity", "Snowy Echoes", R.drawable.cover11, 195000L), // 3 min 15 sec
        Track(12, "Soulful Stride", "Heartfelt Harmony", R.drawable.cover12, 225000L), // 3 min 45 sec
        Track(13, "Morning Dew", "Early Risers", R.drawable.cover13, 45000L), // 45 secs
        Track(14, "Cosmic Journey", "Star Gazers", R.drawable.cover14, 270000L), // 4 min 30 sec
        Track(15, "Dance of the Light", "Night Glow", R.drawable.cover15, 15000L) // 15 secs
    )

    // Function to get the next track by the current track's ID.
    fun getNextTrackById(currentId: Int): Track? {
        val currentIndex = trackList.indexOfFirst { it.id == currentId }

        return if (currentIndex != -1) {
            // If it's the last track, return the first track. Otherwise, return the next track.
            if (currentIndex == trackList.lastIndex) {
                trackList.first()
            } else {
                trackList[currentIndex + 1]
            }
        } else {
            // If the currentId is not found, return null.
            null
        }
    }
}
