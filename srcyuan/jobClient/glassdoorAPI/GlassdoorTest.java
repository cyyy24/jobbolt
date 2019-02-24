package jobClient.glassdoorAPI;



import java.util.List;

import org.json.JSONObject;

import entity.Job;
import jobClient.glassdoorAPI.GlassdoorAPI;


public class GlassdoorTest {
	
	public static void main(String[] args) {
		GlassdoorAPI ga = new GlassdoorAPI();
		List<Job> results = ga.search("new york", "Software Engineer", "Facebook");
		try {
			System.out.println("find " + results.size() + " job results!");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

// Usage in backend: 
// JSONObject result = ga.search("new york", " Software  Engineer", "Facebook");

// Usage in browser/postman, GET request
// example: GET http://localhost:8080/YourJobs/search?location=new%20york&keyword=software%20engineer&company=facebook

// result
/*
 * [
    {
        "job_id": "-1302791822",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Instagram Software Engineer, Android",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=101&ao=456593&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_1745a6e5&cb=1550991971494&jobListingId=2992175474&ugo=cdb872e8-da4c-445d-9507-b69e31eec111"
    },
    {
        "job_id": "-1302791830",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Instagram - Software Engineer, Machine Learning",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=102&ao=456593&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_33072756&cb=1550991971497&jobListingId=2992175466&ugo=cdb872e8-da4c-445d-9507-b69e31eec111"
    },
    {
        "job_id": "-1302792438",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Instagram - Software Engineer, iOS",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=103&ao=456593&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_42f8ad52&cb=1550991971500&jobListingId=2992174858&ugo=cdb872e8-da4c-445d-9507-b69e31eec111"
    },
    {
        "job_id": "-1302792426",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Software Engineer, Infrastructure",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=104&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_e35ed7d2&cb=1550991971502&jobListingId=2992174870&ugo=cdb872e8-da4c-445d-9507-b69e31eec111"
    },
    {
        "job_id": "-1302807408",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Instagram - Manager, Software Engineering",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=105&ao=4120&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_94372ff3&cb=1550991971505&jobListingId=2992159888&ugo=cdb872e8-da4c-445d-9507-b69e31eec111"
    },
    {
        "job_id": "-1302792457",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Manager, Software Engineering - Machine Learning",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=106&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_ed2ae82d&cb=1550991971508&jobListingId=2992174839&ugo=cdb872e8-da4c-445d-9507-b69e31eec111"
    },
    {
        "job_id": "-1176850172",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Manager, Software Engineering - Customer Insights",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=107&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_5da52d2b&cb=1550991971511&jobListingId=3118117124&ugo=cdb872e8-da4c-445d-9507-b69e31eec111"
    },
    {
        "job_id": "-1302797180",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Software Engineer, Web Performance",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=108&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_f2b55982&cb=1550991971513&jobListingId=2992170116&ugo=cdb872e8-da4c-445d-9507-b69e31eec111"
    },
    {
        "job_id": "-1302797359",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Software Engineer, iOS",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=109&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_4a452528&cb=1550991971516&jobListingId=2992169937&ugo=cdb872e8-da4c-445d-9507-b69e31eec111"
    },
    {
        "job_id": "-1302791599",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Manager, Software Engineering - Product Generalist",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=110&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_18066974&cb=1550991971522&jobListingId=2992175697&ugo=cdb872e8-da4c-445d-9507-b69e31eec111"
    },
    {
        "job_id": "-1302795077",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Manager, Software Engineering - Infrastructure",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=201&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_e0d26692&cb=1550991971828&jobListingId=2992172219&ugo=c0297eeb-88a9-4b7a-8a2e-b782dd647f28"
    },
    {
        "job_id": "-1302791793",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Software Engineer, Android",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=202&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_ae30ef91&cb=1550991971831&jobListingId=2992175503&ugo=c0297eeb-88a9-4b7a-8a2e-b782dd647f28"
    },
    {
        "job_id": "-1302786328",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Software Engineer, Product (Full Stack)",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=203&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_6791ad29&cb=1550991971834&jobListingId=2992180968&ugo=c0297eeb-88a9-4b7a-8a2e-b782dd647f28"
    },
    {
        "job_id": "-1302784637",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Software Engineer, Machine Learning",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=204&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_b295588c&cb=1550991971836&jobListingId=2992182659&ugo=c0297eeb-88a9-4b7a-8a2e-b782dd647f28"
    },
    {
        "job_id": "-1302781378",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Data Engineering Manager, Analytics",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=205&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_5535adb0&cb=1550991971839&jobListingId=2992185918&ugo=c0297eeb-88a9-4b7a-8a2e-b782dd647f28"
    },
    {
        "job_id": "-1302796839",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Manager, Production Engineering",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=206&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_639b94dd&cb=1550991971842&jobListingId=2992170457&ugo=c0297eeb-88a9-4b7a-8a2e-b782dd647f28"
    },
    {
        "job_id": "-1198947570",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Production Engineer",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=207&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_7ea8910b&cb=1550991971845&jobListingId=3096019726&ugo=c0297eeb-88a9-4b7a-8a2e-b782dd647f28"
    },
    {
        "job_id": "-1302791766",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Applied Research Scientist, Core Machine Learning",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=208&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_fb70f0e3&cb=1550991971847&jobListingId=2992175530&ugo=c0297eeb-88a9-4b7a-8a2e-b782dd647f28"
    },
    {
        "job_id": "-1265327699",
        "company": "Facebook",
        "location": "New York, NY",
        "title": "Partner Engineer, Media Partnerships",
        "category": "glassdoor category 0",
        "platform": "glassdoor",
        "url": "https://www.glassdoor.com/partner/jobListing.htm?pos=209&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_4e466fed&cb=1550991971850&jobListingId=3029639597&ugo=c0297eeb-88a9-4b7a-8a2e-b782dd647f28"
    }
]
]*/





//
// Glassdoor GET request result:
//

/*
 * {
    "jsessionid": "",
    "success": true,
    "response": {
        "industryFilter": [],
        "salaryFilter": [],
        "trackingPixels": "",
        "isNonUSSearch": false,
        "citiesFilter": [],
        "companiesFilter": [],
        "spellingSuggestions": [],
        "totalRecordCount": 27,
        "lashedLocation": {
            "locationType": "M",
            "id": 615,
            "shortName": "New York City",
            "longName": "New York City, NY"
        },
        "currentPageNumber": 1,
        "attributionURL": "https://www.glassdoor.com/Job/new-york-city-software-engineer-facebook-jobs-SRCH_IL.0,13_IM615_KO14,31_KE32,40.htm",
        "jobListings": [
            {
                "jobCategory": 0,
                "partnerJobViewUrlParams": "pos=101&ao=456593&s=233&guid=1234567890abcdef1234567890abcdef&src=GD_JOB_VIEW&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=n&slr=false&cs=1_527caba1&cb=1550882735245&jobListingId=2992175466",
                "jobTitle": "Instagram - Software Engineer, Machine Learning",
                "easyApplyEnabled": false,
                "source": "Facebook",
                "urgencyIndicator": "",
                "genuineEasyApply": false,
                "applyOnGD": false,
                "aggregatedLocationCount": 1,
                "jobSourceAdTarget": "a1K6A000001ppYEUAY",
                "jobListingId": 2992175466,
                "employer": {
                    "overallRating": 4.4,
                    "showRating": true,
                    "name": "Facebook",
                    "numberOfRatings": 1944,
                    "rating": 4.4,
                    "isEEP": true,
                    "id": 40772
                },
                "jobViewUrl": "/partner/jobListing.htm?pos=101&ao=456593&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_9c1aaced&cb=1550882735245&jobListingId=2992175466&ugo=550cc6c6-d683-4a1c-9e67-3c29b25de453",
                "jobReqId": 3491852394,
                "nativeJobViewUrlParams": "pos=101&ao=456593&tgt=GD_JOB_VIEW&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_9c1aaced&cb=1550882735245&jobListingId=2992175466",
                "eolHash": 0,
                "gdApplyV2Enabled": false,
                "hoursOld": 368,
                "isNew": "false",
                "descriptionFragment": "Our app has played a critical part in forming meaningful communities where people can connect with each other and share what matters most to&hellip;",
                "discoveryDate": "2019-02-07 08:34:30.963",
                "location": "New York, NY",
                "gdApplyEnabled": false,
                "squareLogo": "https://media.glassdoor.com/sqll/40772/facebook-squarelogo-1381810479272.png",
                "isHot": false,
                "sgocId": 0
            },銆�
            {
                "jobCategory": 0,
                "partnerJobViewUrlParams": "pos=102&ao=456593&s=233&guid=1234567890abcdef1234567890abcdef&src=GD_JOB_VIEW&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=n&slr=false&cs=1_763e2a12&cb=1550882735248&jobListingId=2992175474",
                "jobTitle": "Instagram Software Engineer, Android",
                "easyApplyEnabled": false,
                "source": "Facebook",
                "urgencyIndicator": "",
                "genuineEasyApply": false,
                "applyOnGD": false,
                "aggregatedLocationCount": 1,
                "jobSourceAdTarget": "a1K6A000001pr0fUAA",
                "jobListingId": 2992175474,
                "employer": {
                    "overallRating": 4.4,
                    "showRating": true,
                    "name": "Facebook",
                    "numberOfRatings": 1944,
                    "rating": 4.4,
                    "isEEP": true,
                    "id": 40772
                },
                "jobViewUrl": "/partner/jobListing.htm?pos=102&ao=456593&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_b8582d5e&cb=1550882735247&jobListingId=2992175474&ugo=550cc6c6-d683-4a1c-9e67-3c29b25de453",
                "jobReqId": 3720027522,
                "nativeJobViewUrlParams": "pos=102&ao=456593&tgt=GD_JOB_VIEW&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_b8582d5e&cb=1550882735248&jobListingId=2992175474",
                "eolHash": 0,
                "gdApplyV2Enabled": false,
                "hoursOld": 368,
                "isNew": "false",
                "descriptionFragment": "Work closely with our product and design teams to customize the Instagram experience for the Android platform\n   \u2022 Prototype new and redesign&hellip;",
                "discoveryDate": "2019-02-07 08:39:52.633",
                "location": "New York, NY",
                "gdApplyEnabled": false,
                "squareLogo": "https://media.glassdoor.com/sqll/40772/facebook-squarelogo-1381810479272.png",
                "isHot": false,
                "sgocId": 0
            },
            {
                "jobCategory": 0,
                "partnerJobViewUrlParams": "pos=103&ao=456593&s=233&guid=1234567890abcdef1234567890abcdef&src=GD_JOB_VIEW&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=n&slr=false&cs=1_8c9eaa1e&cb=1550882735251&jobListingId=2992174858",
                "jobTitle": "Instagram - Software Engineer, iOS",
                "easyApplyEnabled": false,
                "source": "Facebook",
                "urgencyIndicator": "",
                "genuineEasyApply": false,
                "applyOnGD": false,
                "aggregatedLocationCount": 1,
                "jobSourceAdTarget": "a1K6A000001pr0iUAA",
                "jobListingId": 2992174858,
                "employer": {
                    "overallRating": 4.4,
                    "showRating": true,
                    "name": "Facebook",
                    "numberOfRatings": 1944,
                    "rating": 4.4,
                    "isEEP": true,
                    "id": 40772
                },
                "jobViewUrl": "/partner/jobListing.htm?pos=103&ao=456593&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_42f8ad52&cb=1550882735250&jobListingId=2992174858&ugo=550cc6c6-d683-4a1c-9e67-3c29b25de453",
                "jobReqId": 3724983309,
                "nativeJobViewUrlParams": "pos=103&ao=456593&tgt=GD_JOB_VIEW&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_42f8ad52&cb=1550882735250&jobListingId=2992174858",
                "eolHash": 0,
                "gdApplyV2Enabled": false,
                "hoursOld": 368,
                "isNew": "false",
                "descriptionFragment": "Our app has played a critical part in forming meaningful communities where people can connect with each other and share what matters most to them.Our&hellip;",
                "discoveryDate": "2019-02-07 08:39:14.22",
                "location": "New York, NY",
                "gdApplyEnabled": false,
                "squareLogo": "https://media.glassdoor.com/sqll/40772/facebook-squarelogo-1381810479272.png",
                "isHot": false,
                "sgocId": 0
            },
            {
                "jobCategory": 0,
                "partnerJobViewUrlParams": "pos=104&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=GD_JOB_VIEW&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=n&slr=false&cs=1_c5f6364e&cb=1550882735253&jobListingId=2992180968",
                "jobTitle": "Software Engineer, Product (Full Stack)",
                "easyApplyEnabled": false,
                "source": "Facebook",
                "urgencyIndicator": "",
                "genuineEasyApply": false,
                "applyOnGD": false,
                "aggregatedLocationCount": 1,
                "jobSourceAdTarget": "a1K6A000001pqrDUAQ",
                "jobListingId": 2992180968,
                "employer": {
                    "overallRating": 4.4,
                    "showRating": true,
                    "name": "Facebook",
                    "numberOfRatings": 1944,
                    "rating": 4.4,
                    "isEEP": true,
                    "id": 40772
                },
                "jobViewUrl": "/partner/jobListing.htm?pos=104&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_0b903102&cb=1550882735253&jobListingId=2992180968&ugo=550cc6c6-d683-4a1c-9e67-3c29b25de453",
                "jobReqId": 3720012268,
                "nativeJobViewUrlParams": "pos=104&ao=132920&tgt=GD_JOB_VIEW&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_0b903102&cb=1550882735253&jobListingId=2992180968",
                "eolHash": 0,
                "gdApplyV2Enabled": false,
                "hoursOld": 368,
                "isNew": "false",
                "descriptionFragment": "Whether we're creating new products or helping a small business expand its reach, people at Facebook are builders at heart. Our global teams are&hellip;",
                "discoveryDate": "2019-02-07 08:45:19.317",
                "location": "New York, NY",
                "gdApplyEnabled": false,
                "squareLogo": "https://media.glassdoor.com/sqll/40772/facebook-squarelogo-1381810479272.png",
                "isHot": false,
                "sgocId": 0
            },
            {
                "jobCategory": 0,
                "partnerJobViewUrlParams": "pos=105&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=GD_JOB_VIEW&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=n&slr=false&cs=1_0fcfa447&cb=1550882735256&jobListingId=2992182659",
                "jobTitle": "Software Engineer, Machine Learning",
                "easyApplyEnabled": false,
                "source": "Facebook",
                "urgencyIndicator": "",
                "genuineEasyApply": false,
                "applyOnGD": false,
                "aggregatedLocationCount": 1,
                "jobSourceAdTarget": "a1K6A000001ppYFUAY",
                "jobListingId": 2992182659,
                "employer": {
                    "overallRating": 4.4,
                    "showRating": true,
                    "name": "Facebook",
                    "numberOfRatings": 1944,
                    "rating": 4.4,
                    "isEEP": true,
                    "id": 40772
                },
                "jobViewUrl": "/partner/jobListing.htm?pos=105&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_c1a9a30b&cb=1550882735256&jobListingId=2992182659&ugo=550cc6c6-d683-4a1c-9e67-3c29b25de453",
                "jobReqId": 3639569239,
                "nativeJobViewUrlParams": "pos=105&ao=132920&tgt=GD_JOB_VIEW&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_c1a9a30b&cb=1550882735256&jobListingId=2992182659",
                "eolHash": 0,
                "gdApplyV2Enabled": false,
                "hoursOld": 367,
                "isNew": "false",
                "descriptionFragment": "Whether we're creating new products or helping a small business expand its reach, people at Facebook are builders at heart. Our global teams are&hellip;",
                "discoveryDate": "2019-02-07 08:47:04.77",
                "location": "New York, NY",
                "gdApplyEnabled": false,
                "squareLogo": "https://media.glassdoor.com/sqll/40772/facebook-squarelogo-1381810479272.png",
                "isHot": false,
                "sgocId": 0
            },
            {
                "jobCategory": 0,
                "partnerJobViewUrlParams": "pos=106&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=GD_JOB_VIEW&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=n&slr=false&cs=1_23ee3962&cb=1550882735259&jobListingId=2992170116",
                "jobTitle": "Software Engineer, Web Performance",
                "easyApplyEnabled": false,
                "source": "Facebook",
                "urgencyIndicator": "",
                "genuineEasyApply": false,
                "applyOnGD": false,
                "aggregatedLocationCount": 1,
                "jobSourceAdTarget": "a1K6A000001pujnUAA",
                "jobListingId": 2992170116,
                "employer": {
                    "overallRating": 4.4,
                    "showRating": true,
                    "name": "Facebook",
                    "numberOfRatings": 1944,
                    "rating": 4.4,
                    "isEEP": true,
                    "id": 40772
                },
                "jobViewUrl": "/partner/jobListing.htm?pos=106&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_ed883e2e&cb=1550882735259&jobListingId=2992170116&ugo=550cc6c6-d683-4a1c-9e67-3c29b25de453",
                "jobReqId": 3568540758,
                "nativeJobViewUrlParams": "pos=106&ao=132920&tgt=GD_JOB_VIEW&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_ed883e2e&cb=1550882735259&jobListingId=2992170116",
                "eolHash": 0,
                "gdApplyV2Enabled": false,
                "hoursOld": 368,
                "isNew": "false",
                "descriptionFragment": "Whether we're creating new products or helping a small business expand its reach, people at Facebook are builders at heart. Our global teams are&hellip;",
                "discoveryDate": "2019-02-07 08:34:47.11",
                "location": "New York, NY",
                "gdApplyEnabled": false,
                "squareLogo": "https://media.glassdoor.com/sqll/40772/facebook-squarelogo-1381810479272.png",
                "isHot": false,
                "sgocId": 0
            },
            {
                "jobCategory": 0,
                "partnerJobViewUrlParams": "pos=107&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=GD_JOB_VIEW&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=n&slr=false&cs=1_27be849c&cb=1550882735262&jobListingId=2992172219",
                "jobTitle": "Manager, Software Engineering - Infrastructure",
                "easyApplyEnabled": false,
                "source": "Facebook",
                "urgencyIndicator": "",
                "genuineEasyApply": false,
                "applyOnGD": false,
                "aggregatedLocationCount": 1,
                "jobSourceAdTarget": "a1K6A000001prQNUAY",
                "jobListingId": 2992172219,
                "employer": {
                    "overallRating": 4.4,
                    "showRating": true,
                    "name": "Facebook",
                    "numberOfRatings": 1944,
                    "rating": 4.4,
                    "isEEP": true,
                    "id": 40772
                },
                "jobViewUrl": "/partner/jobListing.htm?pos=107&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_e9d883d0&cb=1550882735262&jobListingId=2992172219&ugo=550cc6c6-d683-4a1c-9e67-3c29b25de453",
                "jobReqId": 3707171896,
                "nativeJobViewUrlParams": "pos=107&ao=132920&tgt=GD_JOB_VIEW&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_e9d883d0&cb=1550882735262&jobListingId=2992172219",
                "eolHash": 0,
                "gdApplyV2Enabled": false,
                "hoursOld": 368,
                "isNew": "false",
                "descriptionFragment": "Whether we're creating new products or helping a small business expand its reach, people at Facebook are builders at heart. Our global teams are&hellip;",
                "discoveryDate": "2019-02-07 08:36:55.083",
                "location": "New York, NY",
                "gdApplyEnabled": false,
                "squareLogo": "https://media.glassdoor.com/sqll/40772/facebook-squarelogo-1381810479272.png",
                "isHot": false,
                "sgocId": 0
            },
            {
                "jobCategory": 0,
                "partnerJobViewUrlParams": "pos=108&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=GD_JOB_VIEW&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=n&slr=false&cs=1_f81345e0&cb=1550882735264&jobListingId=2992174870",
                "jobTitle": "Software Engineer, Infrastructure",
                "easyApplyEnabled": false,
                "source": "Facebook",
                "urgencyIndicator": "",
                "genuineEasyApply": false,
                "applyOnGD": false,
                "aggregatedLocationCount": 1,
                "jobSourceAdTarget": "a1K6A000001prWJUAY",
                "jobListingId": 2992174870,
                "employer": {
                    "overallRating": 4.4,
                    "showRating": true,
                    "name": "Facebook",
                    "numberOfRatings": 1944,
                    "rating": 4.4,
                    "isEEP": true,
                    "id": 40772
                },
                "jobViewUrl": "/partner/jobListing.htm?pos=108&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_367542ac&cb=1550882735264&jobListingId=2992174870&ugo=550cc6c6-d683-4a1c-9e67-3c29b25de453",
                "jobReqId": 3645434427,
                "nativeJobViewUrlParams": "pos=108&ao=132920&tgt=GD_JOB_VIEW&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_367542ac&cb=1550882735264&jobListingId=2992174870",
                "eolHash": 0,
                "gdApplyV2Enabled": false,
                "hoursOld": 368,
                "isNew": "false",
                "descriptionFragment": "Whether we're creating new products or helping a small business expand its reach, people at Facebook are builders at heart. Our global teams are&hellip;",
                "discoveryDate": "2019-02-07 08:39:14.987",
                "location": "New York, NY",
                "gdApplyEnabled": false,
                "squareLogo": "https://media.glassdoor.com/sqll/40772/facebook-squarelogo-1381810479272.png",
                "isHot": false,
                "sgocId": 0
            },
            {
                "jobCategory": 0,
                "partnerJobViewUrlParams": "pos=109&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=GD_JOB_VIEW&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=n&slr=false&cs=1_84232264&cb=1550882735267&jobListingId=2992169937",
                "jobTitle": "Software Engineer, iOS",
                "easyApplyEnabled": false,
                "source": "Facebook",
                "urgencyIndicator": "",
                "genuineEasyApply": false,
                "applyOnGD": false,
                "aggregatedLocationCount": 1,
                "jobSourceAdTarget": "a1K6A000001pr0nUAA",
                "jobListingId": 2992169937,
                "employer": {
                    "overallRating": 4.4,
                    "showRating": true,
                    "name": "Facebook",
                    "numberOfRatings": 1944,
                    "rating": 4.4,
                    "isEEP": true,
                    "id": 40772
                },
                "jobViewUrl": "/partner/jobListing.htm?pos=109&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_4a452528&cb=1550882735267&jobListingId=2992169937&ugo=550cc6c6-d683-4a1c-9e67-3c29b25de453",
                "jobReqId": 3468658366,
                "nativeJobViewUrlParams": "pos=109&ao=132920&tgt=GD_JOB_VIEW&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_4a452528&cb=1550882735267&jobListingId=2992169937",
                "eolHash": 0,
                "gdApplyV2Enabled": false,
                "hoursOld": 368,
                "isNew": "false",
                "descriptionFragment": "Whether we're creating new products or helping a small business expand its reach, people at Facebook are builders at heart. Our global teams are&hellip;",
                "discoveryDate": "2019-02-07 08:34:30.963",
                "location": "New York, NY",
                "gdApplyEnabled": false,
                "squareLogo": "https://media.glassdoor.com/sqll/40772/facebook-squarelogo-1381810479272.png",
                "isHot": false,
                "sgocId": 0
            },
            {
                "jobCategory": 0,
                "partnerJobViewUrlParams": "pos=110&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=GD_JOB_VIEW&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=n&slr=false&cs=1_9d2f4f9f&cb=1550882735270&jobListingId=2992172220",
                "jobTitle": "Instagram - Manager, Software Engineering",
                "easyApplyEnabled": false,
                "source": "Facebook",
                "urgencyIndicator": "",
                "genuineEasyApply": false,
                "applyOnGD": false,
                "aggregatedLocationCount": 1,
                "jobSourceAdTarget": "a1K6A000001prQPUAY",
                "jobListingId": 2992172220,
                "employer": {
                    "overallRating": 4.4,
                    "showRating": true,
                    "name": "Facebook",
                    "numberOfRatings": 1944,
                    "rating": 4.4,
                    "isEEP": true,
                    "id": 40772
                },
                "jobViewUrl": "/partner/jobListing.htm?pos=110&ao=132920&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_534948d3&cb=1550882735270&jobListingId=2992172220&ugo=550cc6c6-d683-4a1c-9e67-3c29b25de453",
                "jobReqId": 3720044098,
                "nativeJobViewUrlParams": "pos=110&ao=132920&tgt=GD_JOB_VIEW&s=233&guid=1234567890abcdef1234567890abcdef&src=API_NETWORK&pao=3410&t=API&extid=14&exst=EOL&ist=&ast=EOL&vt=w&slr=false&cs=1_534948d3&cb=1550882735270&jobListingId=2992172220",
                "eolHash": 0,
                "gdApplyV2Enabled": false,
                "hoursOld": 368,
                "isNew": "false",
                "descriptionFragment": "Provide technical and organizational leadership over the community engineering team\n   \u2022 Communicate cross-functionally and drive engineering&hellip;",
                "discoveryDate": "2019-02-07 08:36:55.113",
                "location": "New York, NY",
                "gdApplyEnabled": false,
                "squareLogo": "https://media.glassdoor.com/sqll/40772/facebook-squarelogo-1381810479272.png",
                "isHot": false,
                "sgocId": 0
            }
        ],
        "totalNumberOfPages": 2
    },
    "status": "OK"
}*/
