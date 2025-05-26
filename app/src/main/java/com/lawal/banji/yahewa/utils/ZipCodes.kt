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
    "50309",  // Des Moines, IA
    "04101", // Portland, ME
    "06103", // Hartford, CT
    "03101", // Manchester, NH
    "03766", // Lebanon, NH
    "05701", // Rutland, VT
    "12207", // Albany, NY
    "14604", // Rochester, NY
    "14202", // Buffalo, NY
    "00918", // San Juan, PR
    "06111", // Newington, CT
    "02903", // Providence, RI
    "19901", // Dover, DE
    "19801", // Wilmington, DE
    "01060", // Northampton, MA
    "02601", // Hyannis, MA
    "04032", // Freeport, ME
    "02840", // Newport, RI
    "08901", // New Brunswick, NJ
    "08540", // Princeton, NJ
    "07030", // Hoboken, NJ
    "06360", // Norwich, CT
    "01810", // Andover, MA
    "04401", // Bangor, ME
    "10573", // Port Chester, NY
    "17602", // Lancaster, PA
    "18701", // Wilkes-Barre, PA
    "14222", // Elmwood Village, Buffalo, NY
    "02886", // Warwick, RI
    "03060", // Nashua, NH
    "01002", // Amherst, MA
    "18505", // Scranton (South), PA
    "68106", // Ralston, NE
    "06510", // New Haven, CT
    "05401", // Burlington, VT
    "02139", // Cambridge, MA
    "06340", // Groton, CT
    "06032", // Farmington, CT
    "12305", // Schenectady, NY
    "13202", // Syracuse, NY
    "17101", // Harrisburg, PA
    "25301", // Charleston, WV
    "26505", // Morgantown, WV
    "24740", // Princeton, WV
    "21502", // Cumberland, MD
    "27949", // Kitty Hawk, NC
    "26847", // Petersburg, WV
    "16335", // Meadville, PA
    "02806", // Barrington, RI
    "19530", // Kutztown, PA
    "06238"  // Coventry, CT
)

fun getRandomZipCode(): String {
    while (zipCodes.isEmpty()) {
        // Keeps trying until the list is non-empty
        Thread.sleep(100) // Prevents a busy loop. Adjust delay as needed.
    }
    return zipCodes.random()
}
