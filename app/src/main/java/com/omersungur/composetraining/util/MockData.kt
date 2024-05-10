package com.omersungur.composetraining.util

import com.omersungur.composetraining.model.BestHouse
import com.omersungur.composetraining.model.Category
import com.omersungur.composetraining.model.RecommendationHouse

val recommendationList = listOf(
    RecommendationHouse(
        name = "The Coach House 1",
        address = "Noida Sec 1",
        imageUrl = "https://res.akamaized.net/domain/image/upload/t_web/v1538713881/bigsmall_Mirvac_house2_twgogv.jpg",
        star = 5.0
    ),
    RecommendationHouse(
        name = "The Coach House 2",
        address = "Noida Sec 2",
        imageUrl = "https://thumbor.forbes.com/thumbor/fit-in/x/https://www.forbes.com/advisor/wp-content/uploads/2021/08/download-7.jpg",
        star = 5.0
    ),
    RecommendationHouse(
        name = "The Coach House 3",
        address = "Noida Sec 3",
        imageUrl = "https://www.houseplans.net/news/wp-content/uploads/2023/07/57260-768.jpeg",
        star = 5.0
    ),
    RecommendationHouse(
        "The Coach House 4",
        "Noida Sec 4",
        "https://www.livehome3d.com/assets/img/articles/design-house/how-to-design-a-house.jpg",
        5.0
    ),
    RecommendationHouse(
        "The Coach House 5",
        "Noida Sec 5",
        "https://res.akamaized.net/domain/image/upload/t_web/v1538713881/bigsmall_Mirvac_house2_twgogv.jpg",
        5.0
    )
)

val bestHouseList = listOf(
    BestHouse(
        "House 1",
        "Noida Sec 16",
        "https://content.app-sources.com/s/40460189946594834/uploads/New_Int_Design/modern_living_room-6712273.jpg?format=webp",
        5.0
    ),
    BestHouse(
        "House 2",
        "Noida Sec 20",
        "https://gatherhomeandlifestyle.com/wp-content/uploads/2022/04/Living-Room-Rooms-in-a-house-2-1.jpg",
        5.0
    )
)

val categoryList = listOf(
    Category("House"),
    Category("Hotels"),
    Category("Shops"),
    Category("Gym"),
    Category("Plots"),
    Category("Example1"),
    Category("Example2"),
)