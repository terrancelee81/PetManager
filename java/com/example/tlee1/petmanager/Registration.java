package com.example.tlee1.petmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registration extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText nm, em, un, p1, p2;
    Button btnsign;
    private EditText petname;
    TextView txtResult;
    private String name, email, userName, pass1, pass2, text;
    private long session;
    private Long when;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        myDb = new DatabaseHelper(this);

        nm = (EditText) findViewById(R.id.editTextName);
        name = nm.getText().toString();
        em = (EditText) findViewById(R.id.editTextEmail);
        email = em.getText().toString();
        un = (EditText) findViewById(R.id.editTextUserName);
        userName = un.getText().toString();
        p1 = (EditText) findViewById(R.id.editTextPass1);
        pass1 = p1.getText().toString();
        p2 = (EditText) findViewById(R.id.editTextPass2);
        pass2 = p2.getText().toString();
        btnsign = (Button) findViewById(R.id.buttonSignUp);
        onSignUpClick();

    }



    public void onSignUpClick() {
        btnsign.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                                           /*boolean isInserted =*/
                        //myDb.insertData(name.getText().toString(),
                        //       email.getText().toString(),
                        //      userName.getText().toString(),
                        //        pass1.getText().toString());
                                           /* if(isInserted =true)*/
                        myDb.insertData(name, email, userName, pass1);
                        Intent intent = new Intent(Registration.this, MainActivity.class);
                        startActivity(intent);

                        //send();

                       /* if (pass1 == pass2) {

                            Toast.makeText(Registration.this, "Information Saved", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(Registration.this, "Information not Saved", Toast.LENGTH_LONG).show();
                                            *//*Intent intent = new Intent(Registration.this, MainActivity.class);
                                            startActivity(intent);*//*

                        }*/
                    }

                }
        );


    }

   /* public void send() {
        Registration entry = new Registration();
        entry.setWhen(getWhen());
        //entry.setTitle(getTitle());
        entry.setUserName(UiUtil.readText(this, R.id.editTextUserName));
        entry.setEmail(UiUtil.readText(this, R.id.editTextEmail));
        entry.setName(UiUtil.readText(this, R.id.editTextName));
        entry.setPass1(UiUtil.readText(this, R.id.editTextPass1));

        Server serv = new Server(this, userName, pass1);
        try {
            serv.send(entry);
            Toast.makeText(Registration.this, "done", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(Registration.this, "fail", Toast.LENGTH_LONG).show();
            // sendStatusMessage(ex.getMessage());
        }

    }


    //    private String name, email, userName, pass1, pass2;
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        //    userName =  UiUtil.readText(this, R.id.editTextUserName);
        return userName;
    }

    public String getPass1() {
        return pass1;
    }

    public long getWhen() {
        return when != null ? when.longValue() : 0L;
    }

    public void setWhen(long when) {
        this.when = when;
    }

    public void setUserName(String userName) {
        //    userName =  UiUtil.readText(this, R.id.editTextUserName);
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    *//*public void setText(String text) {
        this.text = text;
    }*//*


    public static String toJson(Registration reg) {
        // new Gson().toJson(user) would include the hashpassword,
        // which we don't want; so we'd either have to customize
        // the JSON-generation using filters or metadata, or
        // (much simpler) just grab the fields we actually want to send

        HashMap<String, Object> obj = new HashMap<>();
        obj.put("userName", reg.getUsername());
        obj.put("email", reg.getEmail());
        obj.put("name", reg.getName());
        obj.put("pass1", reg.getPass1());


        return new Gson().toJson(obj);
    }

    public static Registration fromJson(String json) throws JSONException {
        JSONObject tmp = new JSONObject(json);
        Registration entry = new Registration();
        entry.setWhen(tmp.getLong("when"));
        entry.setTitle(tmp.getString("title"));
        entry.setUserName(tmp.getString("userName"));
        entry.setEmail(tmp.getString("email"));
        entry.setName(tmp.getString("name"));
        entry.setPass1(tmp.getString("pass1"));


        return entry;

    }

    public void doGet(View v) {
        new AsyncTask<Void, Void, String>() {
            protected void onPreExecute() {
                TextView userName = (TextView) findViewById(R.id.editTextUserName);
                userName.setText("Please wait...");
            }

            protected String doInBackground(Void... params) {
                HttpGet http = new HttpGet("http://httpbin.org/get");
                http.addFormField("userName", "user");
                http.addFormField("password", "pass");
                try {
                    return http.finish();
                } catch (Exception e) {
                    return formatError(e);
                }
            }

            protected void onPostExecute(String txt) {
                setText(txt);
            }
        }.execute();
    }

    public void doPost(View v) {
        new AsyncTask<Void, Void, String>() {
            protected void onPreExecute() {
                TextView userName = (TextView) findViewById(R.id.editTextUserName);
                userName.setText("Please wait...");
            }

            protected String doInBackground(Void... params) {
                HttpPost http = new HttpPost("http://httpbin.org/post", "");
                http.addFormField("userName", "user");
                http.addFormField("password", "pass");
                try {
                    return http.finish();
                } catch (Exception e) {
                    return formatError(e);
                }
            }

            protected void onPostExecute(String txt) {
                setText(txt);
            }
        }.execute();
    }

    public void doGetJson(View v) {
        new AsyncTask<Void, Void, String>() {
            protected void onPreExecute() {
                TextView userName = (TextView) findViewById(R.id.editTextUserName);
                userName.setText("Please wait...");
            }

            protected String doInBackground(Void... params) {
                HttpGet http = new HttpGet("http://httpbin.org/get");
                http.addFormField("userName", "user");
                http.addFormField("password", "pass");
                try {
                    String txt = http.finish();
                    JSONObject json = new JSONObject(txt);
                    StringBuffer rv = new StringBuffer();
                    Iterator<String> it = json.keys();
                    while (it.hasNext()) {
                        String nm = it.next();
                        rv.append(nm + "=" + json.get(nm) + "\n\n");
                    }

                    return rv.toString();
                } catch (Exception e) {
                    return formatError(e);
                }
            }

            protected void onPostExecute(String txt) {
                setText(txt);
            }
        }.execute();
    }

    public void doPutJson(View v) {
        new AsyncTask<Void, Void, String>() {
            protected void onPreExecute() {
                TextView userName = (TextView) findViewById(R.id.editTextUserName);
                userName.setText("Please wait...");
            }

            protected String doInBackground(Void... params) {
                HttpPut http = new HttpPut("https://pets-1332.appspot.com/?op=create");
                try {
                    JSONObject json = new JSONObject();
                    json.put("userName", "user");
                    json.put("password", "pass");

                    return http.finish(json.toString());
                } catch (Exception e) {
                    return formatError(e);
                }
            }

            protected void onPostExecute(String txt) {
                setText(txt);
            }
        }.execute();
    }


    public void doFileUpload(View v) {
        new AsyncTask<Void, Void, String>() {
            protected void onPreExecute() {
                TextView userName = (TextView) findViewById(R.id.editTextUserName);
                userName.setText("Please wait...");
            }

            protected String doInBackground(Void... params) {
                try {
                    HttpUpload http = new HttpUpload("https://pets-1332.appspot.com/?op=create", "UTF-8");
                    http.addFormField("userName", "user");
                    http.addFormField("password", "pass");
                    http.addFilePart("file", "my junky file.txt", "these can be any bytes, even an image, or whatever".getBytes("UTF-8"));

                    String txt = http.finish();

                    // let's see if the service sends back a URL we can follow for more information
                    int httpLoc = txt.indexOf("http://");
                    int crlfLoc = txt.indexOf("\n", httpLoc + 1);
                    if (httpLoc != -1 && crlfLoc != -1) {
                        String feedbackUrl = txt.substring(httpLoc, crlfLoc).trim();
                        String feedbackInfo = new HttpGet(feedbackUrl).finish();

                        // let's see if the "more information" includes the URL where I can check my upload
                        int finalHttpLoc = feedbackInfo.lastIndexOf("http://");
                        if (finalHttpLoc != -1) {
                            String urlOfMyUpload = feedbackInfo.substring(finalHttpLoc).trim();
                            String myUpload = new HttpGet(urlOfMyUpload).finish();

                            return feedbackInfo + "\n\n------------------\n\n" + myUpload;
                        }
                        return feedbackInfo + "\n\n\n(No further information about file upload)";
                    } else {
                        return txt + "\n\n\n(Cannot parse result to get further details)";
                    }
                } catch (Exception e) {
                    return formatError(e);
                }
            }

            protected void onPostExecute(String txt) {
                setText(txt);
            }
        }.execute();
    }

    private void setText(final String str) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
             txtResult = (TextView) (findViewById(R.id.editTextName));
            if (txtResult != null) {
                txtResult.setText(str);
                txtResult.setMovementMethod(new ScrollingMovementMethod());
                txtResult.scrollTo(0, 0);
            }
        } else {
            runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            setText(str);
                        }
                    }
            );
        }
    }

    private String formatError(Exception ex) {
        StringWriter tmp = new StringWriter();
        tmp.append("An exception has occurred...\n");
        ex.printStackTrace(new PrintWriter(tmp));
        return tmp.toString();
    }*/
}


