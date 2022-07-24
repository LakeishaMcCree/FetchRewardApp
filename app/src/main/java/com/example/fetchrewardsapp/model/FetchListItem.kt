package com.example.fetchrewardsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FetchListItem(
    val id: String,
    val listId: String,
    val name: String
): Parcelable
