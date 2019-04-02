package starwars.coding.com.ParkLah.Entity.Carpark;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CarparkAPIInterface {
    @Headers({
            "Content-Type: application/json",
            "accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
            "accept-language: en-GB,en-US;q=0.9,en;q=0.8",
            "cache-control: max-age=0",
            "cookie: __cfduid=d48656288889067da60bd736e2df815de1554091077",
            "upgrade-insecure-requests: 1",
            "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36"})
    @GET("/v1/transport/carpark-availability/")
    Call<CarparkAvailability> getAvailableSlot();

    @Headers({
            "Content-Type: application/json",
            "accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
            "accept-language: en-GB,en-US;q=0.9,en;q=0.8",
            "cache-control: max-age=0",
            "cookie: __cfduid=d48656288889067da60bd736e2df815de1554091077",
            "upgrade-insecure-requests: 1",
            "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36"})
    @GET("/api/action/datastore_search?resource_id=139a3035-e624-4f56-b63f-89ae28d4ae4c&limit=9999")
    Call<CarparkInfo> getAllLots();
}
