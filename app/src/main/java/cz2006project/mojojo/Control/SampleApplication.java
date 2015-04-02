package main.java.cz2006project.mojojo.Control;

/**
 * Created by srishti on 21/3/15.
 */
/*
 *  Copyright (c) 2014, Parse, LLC. All rights reserved.
 *
 *  You are hereby granted a non-exclusive, worldwide, royalty-free license to use,
 *  copy, modify, and distribute this software in source code or binary form for use
 *  in connection with the web services and APIs provided by Parse.
 *
 *  As with any software that integrates with the Parse platform, your use of
 *  this software is subject to the Parse Terms of Service
 *  [https://www.parse.com/about/terms]. This copyright notice shall be
 *  included in all copies or substantial portions of the software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 *  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 *  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 *  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseTwitterUtils;

import cz2006project.mojojo.R;
import main.java.cz2006project.mojojo.Entity.Appointment;


import android.app.Application;
        import android.graphics.drawable.ColorDrawable;
        import android.graphics.drawable.Drawable;
        import android.os.Build;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;

        import com.parse.Parse;
        import com.parse.ParseACL;
        import com.parse.ParseTwitterUtils;
        import com.parse.ParseUser;

  public class SampleApplication extends Application {
            public static final boolean LOG_DEBUG = true;
            public static final boolean LOG_INFO = true;

            @Override
            public void onCreate() {
                super.onCreate();

                Parse.enableLocalDatastore(this);
                Parse.initialize(this, getString(R.string.parse_app_id),
                        getString(R.string.parse_client_key));
                ParseUser.enableAutomaticUser();
                ParseACL defaultACL = new ParseACL();
                ParseTwitterUtils.initialize(getString(R.string.twitter_consumer_key),
                        getString(R.string.twitter_consumer_secret));

                ParseObject.registerSubclass(Appointment.class);
            }

            public static void setCustomTheme(ActionBarActivity actionBarActivity, int primary, int secondary){
                actionBarActivity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(primary));
                if(Build.VERSION.SDK_INT==Build.VERSION_CODES.LOLLIPOP){
                    actionBarActivity.getWindow().setNavigationBarColor(secondary);
                    actionBarActivity.getWindow().setStatusBarColor(secondary);
                }
            }

        }
