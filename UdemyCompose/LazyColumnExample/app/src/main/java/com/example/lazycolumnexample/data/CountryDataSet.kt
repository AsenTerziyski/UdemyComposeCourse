package com.example.lazycolumnexample.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.lazycolumnexample.R

@Composable
fun retrieveCountries(): SnapshotStateList<CountryModel> = remember {
    mutableStateListOf(
        CountryModel(
            "1",
            "Argentina",
            "Arg",
            R.drawable.argentina
        ),
        CountryModel(
            "2",
            "Brazil",
            "Brz",
            R.drawable.brazil
        ),
        CountryModel(
            "3",
            "Bulgaria",
            "Blg",
            R.drawable.bulgaria
        ),
        CountryModel(
            "4",
            "France",
            "Frnc",
            R.drawable.france
        ),
        CountryModel(
            "5",
            "Germany",
            "Grmn",
            R.drawable.germany
        ),
        CountryModel(
            "6",
            "Ireland",
            "Irlnd",
            R.drawable.ireland
        ),
        CountryModel(
            "7",
            "Italy",
            "Itl",
            R.drawable.italy
        ),
        CountryModel(
            "8",
            "Netherlands",
            "Nthrlnd",
            R.drawable.netherlands
        ),
        CountryModel(
            "9",
            "Romania",
            "Rmn",
            R.drawable.romania
        ),
        CountryModel(
            "10",
            "Slovakia",
            "Slvk",
            R.drawable.slovakia
        ),
        CountryModel(
            "11",
            "Spain",
            "Spn",
            R.drawable.spain
        ),
        CountryModel(
            "12",
            "Turkey",
            "Trk",
            R.drawable.turkey
        )
    )
}
