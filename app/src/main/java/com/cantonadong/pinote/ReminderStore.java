package com.cantonadong.pinote;
import android.content.Context;import org.json.*;import java.util.*;
final class ReminderStore{
 static final class Todo{String text;boolean done;Todo(String t,boolean d){text=t;done=d;}}
 static final class Reminder{long id,updated;String title,type,text;boolean pinned,alert;ArrayList<Todo> todos=new ArrayList<>();Reminder(long i){id=i;updated=System.currentTimeMillis();title="";type="text";text="";}}
 static final ArrayList<Reminder> items=new ArrayList<>();
 static void load(Context c){items.clear();try{JSONArray a=new JSONArray(c.getSharedPreferences("pinote",0).getString("data","[]"));for(int i=0;i<a.length();i++){JSONObject o=a.getJSONObject(i);Reminder r=new Reminder(o.optLong("id"));r.updated=o.optLong("updated");r.title=o.optString("title");r.type=o.optString("type","text");r.text=o.optString("text");r.pinned=o.optBoolean("pinned");r.alert=o.optBoolean("alert");JSONArray ts=o.optJSONArray("todos");if(ts!=null)for(int j=0;j<ts.length();j++){JSONObject t=ts.getJSONObject(j);r.todos.add(new Todo(t.optString("text"),t.optBoolean("done")));}items.add(r);}}catch(Exception ignored){}}
 static void save(Context c){try{JSONArray a=new JSONArray();for(Reminder r:items){JSONObject o=new JSONObject().put("id",r.id).put("updated",r.updated).put("title",r.title).put("type",r.type).put("text",r.text).put("pinned",r.pinned).put("alert",r.alert);JSONArray ts=new JSONArray();for(Todo t:r.todos)ts.put(new JSONObject().put("text",t.text).put("done",t.done));a.put(o.put("todos",ts));}c.getSharedPreferences("pinote",0).edit().putString("data",a.toString()).apply();ReminderService.sync(c);}catch(Exception ignored){}}
 static Reminder find(long id){for(Reminder r:items)if(r.id==id)return r;return null;}static Reminder pinned(){for(Reminder r:items)if(r.pinned)return r;return null;}static void pin(Reminder target){for(Reminder r:items)r.pinned=r==target;}
}
