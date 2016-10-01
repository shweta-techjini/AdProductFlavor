package com.udacity.gradle.builditbigger.network;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.udacity.gradle.builditbigger.listener.EndpointsListener;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Shweta on 9/30/16.
 */
public class EndpointsAsycTaskTest extends ApplicationTestCase<Application> implements EndpointsListener {

    private EndpointsAsycTask endpointsAsycTask;
    private CountDownLatch countDownLatch;

    public EndpointsAsycTaskTest() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        Log.d("setUp", "setup request");
        countDownLatch = new CountDownLatch(1);
    }

    @Override
    public void tearDown() throws Exception {
        Log.d("tearDown", "tear down request");
        countDownLatch.countDown();
    }

    public void testEndPointRequest() throws Exception {
        Log.d("testEndPointRequest", "start request");
        endpointsAsycTask = new EndpointsAsycTask(this);
        endpointsAsycTask.execute();
        countDownLatch.await();
    }

    @Override
    public void resultFromEndPoint(String response) {
        Log.d("resultFromEndPoint", "response:: "+ response);
        assertNotNull(response);
        countDownLatch.countDown();
    }
}