package alarish.hicss.hicss;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.Toast;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {
    ListView listv;
    SharedPreferences myshared;
    SharedPreferences.Editor editeshared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        myshared = getSharedPreferences("savename", Context.MODE_PRIVATE);
        editeshared = myshared.edit();
        String userid = myshared.getString("usid", "n");
      /*  if (ConnectionManager.isIntenetConectted(this)) {
            new MyCustome().execute("http://fatmanoha.com/university/score.php?userid=" + userid);
        } else
            Toast.makeText(this, "Please check internet", Toast.LENGTH_SHORT).show();

*/
    }


    public class myCustomeAdapter extends BaseAdapter {
        List<CoursesModelList> myarray = new ArrayList<>();

        public myCustomeAdapter(List<CoursesModelList> myarray) {
            this.myarray = myarray;
        }

        @Override
        public int getCount() {
            return myarray.size();
        }

        @Override
        public Object getItem(int position) {
            return myarray.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.courses_list_item, null);
            TextView cou_name = (TextView) view.findViewById(R.id.cou_name);
            TextView dr_namr = (TextView) view.findViewById(R.id.dr_namr);
            TextView book_name = (TextView) view.findViewById(R.id.book_name);
            TextView full_mark = (TextView) view.findViewById(R.id.full_mark);
            TextView stu_mark = (TextView) view.findViewById(R.id.stu_mark);
            TextView stu_Percentage = (TextView) view.findViewById(R.id.stu_Percentage);
            TextView stu_grade = (TextView) view.findViewById(R.id.stu_grade);

            //txttitle.setText(myarray.get(position).title);
            return view;
        }
    }

    public class MyCustome extends AsyncTask<String, String, List<CoursesModelList>> {

        @Override
        protected List<CoursesModelList> doInBackground(String... strings) {
            InputStream inputStream = null;
            HttpURLConnection urlConnection = null;
            StringBuilder stringBuilder = null;
            try {

                URL myurl = new URL(strings[0]);

                urlConnection = (HttpURLConnection) myurl.openConnection(

                );
                //esma3el by2ol fe 3 methddth mohmen


                //انا كده روحت للسيرفر وبقوله اعرضلي كل البيانات الي ف الرابط دا
                // وخزنتهم ف المتغير الي انا عملته جديدinputstream

                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                // OutputStream outputStream=new BufferedOutputStream(urlConnection.getOutputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                //BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
                stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                    //append اضافه
                    inputStream.close();
                    urlConnection.disconnect();
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String res = stringBuilder.toString();
            List<CoursesModelList> result = new ArrayList<>();
            try {
                JSONArray jsrray = new JSONArray(res);
                JSONObject object = null;
                for (int i = 0; i < jsrray.length(); i++) {
                    object = jsrray.getJSONObject(i);
                    result.add(new CoursesModelList(object.getString("cou_name"),
                            object.getString("dr_name"),
                            object.getString("book_name"),
                            object.getInt("full_mark"),
                            object.getInt("stu_mark"),
                            object.getString("stu_Percentage"),
                            object.getString("stu_grade")


                    ));

                }

            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            //   String res="";
            //بحول الداتا الي جبتها من السيرفروخزنتها ف inputstreem دلوقتي بحولها ل bufferd

            return result;
        }

        @Override
        protected void onPostExecute(List<CoursesModelList> res) {
            super.onPostExecute(res);
            myCustomeAdapter adapter = new myCustomeAdapter(res);
            listv.setAdapter(adapter);

        }


    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.home_activity_menu, menu);


        return true;

    }


    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                Intent i = new Intent(CoursesActivity.this, MainActivity.class);
                startActivity(i);

                showToast("Home");

                return true;


            case R.id.logout: {
                showToast("log out");
                SharedPreferences preferences = getSharedPreferences("savename", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();

                Intent in = new Intent(CoursesActivity.this, MainActivity.class);
                startActivity(in);
                return true;


            }


            case R.id.close:
                AlertDialog.Builder F = new AlertDialog.Builder(this);
                F.setMessage(" Do You Want To Exit ? ")
                        .setTitle("Close Application");
                F.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(CoursesActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                    }
                });
                F.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(CoursesActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialoug = F.show();


                showToast("Exit");

                return true;


            default:

                return super.

                        onOptionsItemSelected(item);
        }
    }

    public void showToast(String message)

    {

        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);

        toast.show();

    }
}


