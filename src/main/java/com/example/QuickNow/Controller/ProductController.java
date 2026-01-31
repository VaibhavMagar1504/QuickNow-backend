package com.example.QuickNow.Controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.QuickNow.Model.Product;
import com.example.QuickNow.Service.ProductService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService service;

    // ImgBB upload method
    private String uploadToImgBB(MultipartFile file) throws IOException {

        String apiKey = "db583bb8029f7fb659fbef6e0d8411a1";
        String url = "https://api.imgbb.com/1/upload?key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", Base64.getEncoder().encodeToString(file.getBytes()));

        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url, request, String.class);

        JSONObject json = new JSONObject(response.getBody());
        return json.getJSONObject("data").getString("url");
    }

    // Add product
    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestPart Product product,
                                             @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            String imageUrl = uploadToImgBB(imageFile);
            product.setImageUrl(imageUrl);
            service.addProduct(product);
            return new ResponseEntity<>("Product saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Image upload failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allProduct")
    public ResponseEntity<List<Product>> getAllProduct() {
        return new ResponseEntity<>(service.getAllProduct(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        boolean result = service.deleteProduct(id);
        if (result) {
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/productUpdate/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,
                                                @RequestBody Product product) {
        Product updated = service.productUpdate(id, product);
        if (updated != null) {
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Update failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product p = service.getProduct(id);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/searchProduct/{keyword}")
    public ResponseEntity<List<Product>> searchProduct(@PathVariable String keyword) {
        List<Product> products = service.searchProduct(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
