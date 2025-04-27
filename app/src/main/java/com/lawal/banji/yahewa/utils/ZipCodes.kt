package com.lawal.banji.yahewa.utils

val zipCodes = listOf(
    "10001", // New York, NY
    "90001", // Los Angeles, CA
    "60601", // Chicago, IL
    "77001", // Houston, TX
    "85001", // Phoenix, AZ
    "19104", // Philadelphia, PA
    "75201", // Dallas, TX
    "92101", // San Diego, CA
    "78205", // San Antonio, TX
    "33101", // Miami, FL
    "30301", // Atlanta, GA
    "80201", // Denver, CO
    "98101", // Seattle, WA
    "96801", // Honolulu, HI
    "46201", // Indianapolis, IN
    "64101", // Kansas City, MO
    "37201", // Nashville, TN
    "73101", // Oklahoma City, OK
    "55401", // Minneapolis, MN
    "43201", // Columbus, OH
    "85054", // Scottsdale, AZ
    "46204", // Carmel, IN
    "27701", // Durham, NC
    "22901", // Charlottesville, VA
    "15201", // Pittsburgh, PA
    "53703", // Madison, WI
    "28801", // Asheville, NC
    "30303", // Atlanta, GA
    "68102", // Omaha, NE
    "29201", // Columbia, SC
    "96813", // Honolulu, HI
    "32601", // Gainesville, FL
    "37402", // Chattanooga, TN
    "20001", // Washington, DC
    "99201", // Spokane, WA
    "23510", // Norfolk, VA
    "27513", // Cary, NC
    "85281", // Tempe, AZ
    "72201", // Little Rock, AR
    "40202", // Louisville, KY
    "44101", // Cleveland, OH
    "53715", // Madison, WI
    "18503", // Scranton, PA
    "31401", // Savannah, GA
    "27601", // Raleigh, NC
    "61602", // Peoria, IL
    "58102", // Fargo, ND
    "83702", // Boise, ID
    "50309"  // Des Moines, IA
)

fun getRandomZipCode(): String {
    return zipCodes.random()
}