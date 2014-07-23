
/*DHAUMIK PATEL
C0633208
* IPCT GROUP-A*/

package dhaumikkumar.people_notes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
    FavoriteList favList = new FavoriteList();
    Button add,checkcount,list;
    Context context = this;
    DatabaseHandler db;
    ListView listView;
    List<FavoriteList> favoriteList;
    LinearLayout layout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button)findViewById(R.id.add);
        add.setOnClickListener(addOnClick);
        checkcount = (Button)findViewById(R.id.checkcount);
        checkcount.setOnClickListener(checkcountOnClick);
        list = (Button)findViewById(R.id.list);
        list.setOnClickListener(listOnClick);
        layout = (LinearLayout)findViewById(R.id.layout);
        listView = (ListView)findViewById(R.id.listView);
        db = new DatabaseHandler(this);

        //db.removeFav();

  /*favList = db.getFavList();
  Toast.makeText(getApplicationContext(), ""+favList.getSongname(), Toast.LENGTH_LONG).show();*/
    }

    View.OnClickListener addOnClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.row);
            dialog.setTitle("ADD PEOPLE INFORMATION");
            final EditText name = (EditText) dialog.findViewById(R.id.nameEditText);
            final EditText age = (EditText) dialog.findViewById(R.id.ageEditText);
            Button Add = (Button) dialog.findViewById(R.id.addButton);
            Add.setText("Add");
            Add.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(name.getText().toString() != null && name.getText().toString().length() >0 ){
                        if(age.getText().toString() != null && age.getText().toString().length() >0 ){
                            db.adddata(context, name.getText().toString(), age.getText().toString());
                            favoriteList = db.getFavList();
                            listView.setAdapter(new ViewAdapter());
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(), "Please Enter the Age", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Please Enter the Name", Toast.LENGTH_LONG).show();
                    }
                }
            });
            dialog.show();
        }
    };

    View.OnClickListener checkcountOnClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int uyu = db.getCount();
            Toast.makeText(getApplicationContext(), ""+uyu, Toast.LENGTH_LONG).show();
        }
    };

    View.OnClickListener listOnClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            favoriteList = db.getFavList();
            listView.setAdapter(new ViewAdapter());
        }
    };

    public class ViewAdapter extends BaseAdapter {

        LayoutInflater mInflater;

        public ViewAdapter() {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return favoriteList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.listitem,null);
            }

            final TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
            nameText.setText("Name : "+favoriteList.get(position).getName());
            final TextView ageText = (TextView) convertView.findViewById(R.id.ageText);
            ageText.setText("Age : "+favoriteList.get(position).getAge());

            final Button delete = (Button) convertView.findViewById(R.id.delete);
            delete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.removeFav(favoriteList.get(position).getId());
                    notifyDataSetChanged();
                    favoriteList = db.getFavList();
                    listView.setAdapter(new ViewAdapter());
                }
            });
            return convertView;
        }
    }

}
