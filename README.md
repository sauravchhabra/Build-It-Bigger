# Build-It-Bigger
5th Project for Advanced Android Nanodegree at Udacity.

Create an app with multiple flavors that uses multiple libraries and Google Cloud Endpoints. The app consists of four modules. A Java library that provides jokes, a Google Cloud Endpoints (GCE) project that serves those jokes, an Android Library containing an activity for displaying jokes, and an Android app that fetches jokes from the GCE module and passes them to the Android Library for display.


Note:
To run the project on phone rather than an emulator, in JokeAsyncTask I changed setRootUrl to use my laptop's IP address, and in the backend module's build.gradle file I added

appengine {
    run {
        host = "0.0.0.0"
        port = 8080
    }
}
So comment out the above from the backend build.gradle file if you're running on an emulator (plus changing setRootUrl to the emulator localhost IP address 10.0.2.2), or change setRootUrl to your own system's IP address if you're using a physical device.
