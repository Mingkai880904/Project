#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>
int led = 2;

const char* ssid = "iPhone";
const char* password = "77777777";

WiFiServer server(80);
WiFiClient client;
String serverURL = "172.20.10.3";
String url = "";

#define s1 D1
#define s2 D0

void setup(){
  pinMode(led,OUTPUT);
  Serial.begin(115200);
  
  Serial.print( "Start..." );
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println(".");
  }
}

void loop(){
  float w = analogRead(s1);
  float m = analogRead(s2);
  url = "/Esp.PHP?w=" + String(w) + "&m=" + String(m);
  if (isnan(w) || isnan(m) ) {
    Serial.println("無法從WAT+SOI傳感器讀取!");
    return;
  }
  if (client.connect(serverURL, 80)) {
    client.println("GET " + url + " HTTP/1.1");
    client.println("HOST: " + serverURL);
    client.println("Connection: close");
    client.println();
    client.stop();

    Serial.print("水位: ");
    Serial.print(w);
    Serial.println("%\t");
    Serial.print("土壤溼度: ");
    Serial.print(m);
    Serial.println("%\t");
    if((w>30)||(m>30))
    {
      digitalWrite(led,LOW);
    }
    else
    {
      digitalWrite(led,HIGH);
    }
  }
    delay(1000);
}
