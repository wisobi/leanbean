package com.wisobi.leanbean;

/**
 * Created by bjork on 24/02/15.
 */
public final class ApplicationConfig {

    private ApplicationConfig() {
        // no instance
    }

    /**
     * Specify the name of your application. If the application name is
     * {@code null} or blank, the application will log a warning. Suggested
     * format is "MyCompany-Application/1.0".
     */
    static final String APPLICATION_NAME = "Wisobi-LeanBean/0.1";

    /**
     * Specify the package name of the app.
     */
    static final String PACKAGE_NAME = "com.wisobi.leanbean";

    /**
     * Authentication.
     * <p>
     * Installed application: Leave this string empty and copy or
     * edit resources/client_secrets.json.
     * </p>
     * <p>
     * Service accounts: Enter the service
     * account email and add your key.p12 file to the resources directory.
     * </p>
     */
    static final String SERVICE_ACCOUNT_EMAIL = "965914891629-9260djt2492a1d6q7v7qlith1kb2vpni@developer.gserviceaccount.com";

    /**
     * Specify the apk file path of the apk to upload, i.e. /resources/your_apk.apk
     * <p>
     * This needs to be set for running {@link BasicUploadApk} and {@link UploadApkWithListing}
     * samples.
     * </p>
     */
    public static final String APK_FILE_PATH = "/android-release.apk";

}