package com.wisobi.leanbean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.FileContent;
import com.google.api.client.repackaged.com.google.common.base.Preconditions;
import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisher.Edits;
import com.google.api.services.androidpublisher.AndroidPublisher.Edits.Apks.Upload;
import com.google.api.services.androidpublisher.AndroidPublisher.Edits.Commit;
import com.google.api.services.androidpublisher.AndroidPublisher.Edits.Insert;
import com.google.api.services.androidpublisher.AndroidPublisher.Edits.Tracks.Update;
import com.google.api.services.androidpublisher.model.Apk;
import com.google.api.services.androidpublisher.model.AppEdit;
import com.google.api.services.androidpublisher.model.Track;

/**
 * Uploads an apk to the alpha track.
 */
public class BasicUploadApk {

    private static final Log log = LogFactory.getLog(BasicUploadApk.class);

    /**
     * Track for uploading the apk, can be 'alpha', beta', 'production' or
     * 'rollout'.
     */
    private static final String TRACK_ALPHA = "alpha";

    public static void main(String[] args) {
        try {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(ApplicationConfig.PACKAGE_NAME),
                    "ApplicationConfig.PACKAGE_NAME cannot be null or empty!");

            // Create the API service.
            AndroidPublisher service = AndroidPublisherHelper.init(
                    ApplicationConfig.APPLICATION_NAME, ApplicationConfig.SERVICE_ACCOUNT_EMAIL);
            final Edits edits = service.edits();

            // Create a new edit to make changes to your listing.
            Insert editRequest = edits
                    .insert(ApplicationConfig.PACKAGE_NAME,
                            null /** no content */);
            AppEdit edit = editRequest.execute();
            final String editId = edit.getId();
            log.info(String.format("Created edit with id: %s", editId));

            // Upload new apk to developer console
            final InputStream is = BasicUploadApk.class.getResourceAsStream((ApplicationConfig.APK_FILE_PATH));
            File apkTmpFile = AndroidPublisherHelper.getTempFile(is, "apk");
            URL resource = BasicUploadApk.class.getResource(ApplicationConfig.APK_FILE_PATH);
            //final String apkPath = BasicUploadApk.class
            //       .getResource(ApplicationConfig.APK_FILE_PATH)
            //        .toURI().getPath();
            final AbstractInputStreamContent apkFile =
                    new FileContent(AndroidPublisherHelper.MIME_TYPE_APK, apkTmpFile);
            Upload uploadRequest = edits
                    .apks()
                    .upload(ApplicationConfig.PACKAGE_NAME,
                            editId,
                            apkFile);
            Apk apk = uploadRequest.execute();
            log.info(String.format("Version code %d has been uploaded",
                    apk.getVersionCode()));

            // Assign apk to alpha track.
            List<Integer> apkVersionCodes = new ArrayList<>();
            apkVersionCodes.add(apk.getVersionCode());
            Update updateTrackRequest = edits
                    .tracks()
                    .update(ApplicationConfig.PACKAGE_NAME,
                            editId,
                            TRACK_ALPHA,
                            new Track().setVersionCodes(apkVersionCodes));
            Track updatedTrack = updateTrackRequest.execute();
            log.info(String.format("Track %s has been updated.", updatedTrack.getTrack()));

            // Commit changes for edit.
            Commit commitRequest = edits.commit(ApplicationConfig.PACKAGE_NAME, editId);
            AppEdit appEdit = commitRequest.execute();
            log.info(String.format("App edit with id %s has been comitted", appEdit.getId()));

        } catch (IOException | GeneralSecurityException ex) {
            log.error("Excpetion was thrown while uploading apk to alpha track", ex);
        }
    }
}