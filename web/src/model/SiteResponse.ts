import Site from "./Site";

export default class SiteResponse {
    constructor(
        public developerMessage: string,
        public entity: Site,
        public errorMessage: [],
    ) {
    }
}