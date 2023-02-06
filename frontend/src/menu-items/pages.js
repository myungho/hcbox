// assets
import { LoginOutlined, SkinOutlined, HomeOutlined } from '@ant-design/icons';

// icons
const icons = {
    LoginOutlined,
    SkinOutlined,
    HomeOutlined
};

// ==============================|| MENU ITEMS - EXTRA PAGES ||============================== //

const pages = {
    id: 'Management',
    title: 'Management',
    type: 'group',
    children: [
        {
            id: 'products',
            title: 'Product',
            type: 'item',
            url: '/products',
            icon: icons.SkinOutlined,
            breadcrumbs: true
        },
        {
            id: 'schools',
            title: 'Schools',
            type: 'item',
            url: '/schools',
            icon: icons.HomeOutlined,
            breadcrumbs: true
        }
    ]
};

export default pages;
