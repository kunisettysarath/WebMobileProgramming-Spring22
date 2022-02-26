import { Component, ElementRef, OnInit, ViewChild } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Headers } from "@angular/http";

@Component({
  selector: "app-search-recipe",
  templateUrl: "./search-recipe.component.html",
  styleUrls: ["./search-recipe.component.css"],
})
export class SearchRecipeComponent {
  @ViewChild("recipe") recipes: ElementRef;
  @ViewChild("place") places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList = [];
  recipeList = [];
  currentLat: any;
  currentLong: any;
  geolocationPosition: any;
  condition = false;

  constructor(private _http: HttpClient) {}

  getVenues() {
    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;
    this.condition = true;
    if (this.recipeValue !== null) {
      var recipeUrl =
        "https://api.edamam.com/api/recipes/v2?type=public&q=" +
        this.recipeValue +
        "&app_id=57543aa4&app_key=1a9975f110ffe837754777ebeb4731d4";
      this._http.get(recipeUrl).subscribe((data) => {
        let recipes = data["hits"];
        console.log(recipes);
        recipes.map((set) => {
          let recipe = set["recipe"];
          const individualRecepie = {
            name: set.recipe.label,
            url: set.recipe.url,
            icon: set.recipe.image,
          };
          this.recipeList.push(individualRecepie);
        });
      });
    }

    if (
      this.placeValue != null &&
      this.placeValue !== "" &&
      this.recipeValue != null &&
      this.recipeValue !== ""
    ) {
      this.venueList = [];
      var placeUrl =
        "https://api.foursquare.com/v3/places/search?query=" +
        this.recipeValue +
        "&near=" +
        this.placeValue +
        "&v=20220222";
      const head = new HttpHeaders({
        Accept: "application/json",
        Authorization: "fsq3bHh0Csm2oGdkf+mHOrGlzRxjDGxvI7mDGIyuPMhofdU=",
      });
      this._http.get(placeUrl, { headers: head }).subscribe((data) => {
        let venues = data["results"];
        console.log(data["results"][5].location.dma);
        venues.map((set, index) => {
          const individualVenue = {
            name: set.name,
            location: {
              venueAddress: [
                set.location.formatted_address,
                set.location.locality,
                set.location.country,
              ],
            },
          };
          this.venueList.push(individualVenue);
        });
      });
    }
  }
}
